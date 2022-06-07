package com.example.myapplication.bottom.dairy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bumptech.glide.manager.SupportRequestManagerFragment
import com.example.myapplication.R
import com.example.myapplication.bottom.dairy.dairy_post.Post
import com.example.myapplication.bottom.dairy.dairy_post.PostService
import com.example.myapplication.bottom.dairy.dairy_post.PostState
import com.example.myapplication.bottom.dairy.dairy_post.UserName
import com.example.myapplication.bottom.home.HomeFragment
import com.example.myapplication.databinding.FragmentDairyBinding
import com.kakao.sdk.newtoneapi.SpeechRecognizeListener
import com.kakao.sdk.newtoneapi.SpeechRecognizerClient
import com.kakao.sdk.newtoneapi.SpeechRecognizerManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DairyFragment : Fragment() {

    private var _binding: FragmentDairyBinding? = null


    private val binding get() = _binding!!
    var add_text: String = ""
    var state: Int = 0
    var index: Int = 0


    private fun startUsingSpeechSDK() {


        val builder = SpeechRecognizerClient.Builder()
            .setServiceType(SpeechRecognizerClient.SERVICE_TYPE_DICTATION)
            .setGlobalTimeOut(3000)
        //클라이언트 생성
        var client = builder.build()


        //Callback
        client.setSpeechRecognizeListener(object : SpeechRecognizeListener {
            //콜백함수들
            override fun onReady() {
                Log.d("onReady", "모든 하드웨어 및 오디오 서비스가 준비되었습니다.")
            }

            override fun onBeginningOfSpeech() {
                Log.d("onBeginningOfspeech", "사용자가 말을 하기 시작했습니다.")

            }

            override fun onEndOfSpeech() {
                Log.d("onEndOfSpeech", "사용자의 말하기가 끝이 났습니다. 데이터를 서버로 전달합니다.")


            }

            override fun onPartialResult(partialResult: String?) {
                //현재 인식된 음성테이터 문자열을 출력해 준다. 여러번 호출됨. 필요에 따라 사용하면 됨.
                Log.d("onPartialResult", "현재까지 인식된 문자열:" + partialResult)
                if (state == 2) {
                    client.cancelRecording()

                    state = 0

                }

            }

            /*
                최종결과 - 음성입력이 종료 혹은 stopRecording()이 호출되고 서버에 질의가 완료되고 나서 호출됨
                Bundle에 ArrayList로 값을 받음. 신뢰도가 높음 것 부터...
                 */
            override fun onResults(results: Bundle?) {
                val texts =
                    results?.getStringArrayList(SpeechRecognizerClient.KEY_RECOGNITION_RESULTS)
                val confs =
                    results?.getIntegerArrayList(SpeechRecognizerClient.KEY_CONFIDENCE_VALUES)

                Log.d("", texts?.get(0).toString())
                Log.d("addtext", add_text)

                binding.diaryPost.getText()
                    .insert(binding.diaryPost.getSelectionStart(), "${texts?.get(0).toString()} ");


                //정확도가 높은 첫번째 결과값을 텍스트뷰에 출력

            }


            override fun onAudioLevel(audioLevel: Float) {
                //Log.d(TAG, "Audio Level(0~1): " + audioLevel.toString())
            }

            override fun onError(errorCode: Int, errorMsg: String?) {
                //에러 출력 해 봄

                Log.d("onError", "Error: " + errorMsg)
                if (state == 1) {
                    //음성인식 시작함
                    client.cancelRecording()
                    client.startRecording(true)
                    Log.d("error", "loostart")


                }
                if (state == 2) {
                    state = 0
                }


            }

            override fun onFinished() {

                Log.d("finish", "end")

                if (state == 1) {
                    //음성인식 시작함
                    client.startRecording(true)
                    Log.d("Loop", "start")


                }
                if (state == 2) {
                    state = 0
                }
                Log.d("Loop", "${state}")


            }
        })


        client.startRecording(true)


    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {



        Log.d("create", "프래그먼트 실행")
        val DairyViewModel =
            ViewModelProvider(this).get(DairyViewModel::class.java)
        _binding = FragmentDairyBinding.inflate(inflater, container, false)
        val root: View = binding.root


        var retrofit = Retrofit.Builder()
            .baseUrl("http://3.0.128.249:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var postservice: PostService = retrofit.create(PostService::class.java)

        binding.postButton.setOnClickListener {


//            val author = UserName.username
            val author = "iot"
            val title = binding.diaryTitle.text.toString()
            val content = binding.diaryPost.text.toString()

            val post = Post(author, title, content)


            postservice.requestPost(post).enqueue(object: Callback<PostState> {
                override fun onResponse(call: Call<PostState>, response: Response<PostState>) {
                    if(response.isSuccessful) {
                        val result = response.body()
                        Log.d("게시물", "${result?.code}")
                        Log.d("게시물", "${result?.msg}")
                        Toast.makeText(context, "${result?.msg}", Toast.LENGTH_SHORT).show()

                    } else {
                        Log.d("게시물 등록 에러 ", response.errorBody()!!.string())
                        Toast.makeText(context, "게시물 등록 에러", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<PostState>, t: Throwable) {
                    Log.e("게시물 등록 실패", t.localizedMessage)
                    Toast.makeText(context, "게시물 등록 실패", Toast.LENGTH_SHORT).show()
                }


            }

            )



            binding.diaryPost.setText("")
            binding.diaryTitle.setText("")
            state = 2
            if (state == 2){
                state = 0
            }

            findNavController().navigate(R.id.dairyDetailFragment)


        }





            binding.micButton.setOnClickListener {
                state += 1

                Log.d("state", "${state}")


                if (state == 1) {
                    SpeechRecognizerManager.getInstance().initializeLibrary(context)
                    startUsingSpeechSDK()


                }


            }


            return root
        }


        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
            SpeechRecognizerManager.getInstance().finalizeLibrary()
        }
    }





