package com.example.myapplication.bottom.calender

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.room.Update
import com.example.myapplication.bottom.dairy.dairy_post.UserName
import com.example.myapplication.databinding.FragmentCalenderBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


class CalenderFragment : Fragment() {

    private var _binding: FragmentCalenderBinding? = null
    private val binding get() = _binding!!


    interface historyService {
        @PUT("post/{id}/")
        fun update(@Path("id") id: Int, @Body updateDate:updateDate): retrofit2.Call<DataState>
        @DELETE("post/{id}/")
        fun delete(@Path("id") id: Int): Call<DataState?>
    }
        data class updateDate(
            val author: String,
//            val title: String,
            val content: String,
        )
        data class DataState(
            val code: String,
            val msg: String
        )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalenderBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.historybox.apply {
//            arguments?.getString("content")
            this.setText("${arguments?.getString("content")}")

        }



            val retrofit = Retrofit.Builder()
                    .baseUrl("http://3.0.128.249:8000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            val service= retrofit.create(historyService::class.java)


            binding.modify.setOnClickListener {

//                val title =binding.diarytitle.text.toString()
                val content = binding.historybox.text.toString()
                val author = UserName.username
                val Update = updateDate(content,author)

                    service.update(id, Update) .enqueue(object : Callback<DataState> {

                            override fun onResponse(
                                call: Call<DataState>,
                                response: Response<DataState>
                            ) {
                                if (response.isSuccessful) {
                                    val result = response.body()
                                    Log.d("게시물", "${result?.code}")
                                    Log.d("게시물", "${result?.msg}")
                                    Toast.makeText(context, "${result?.msg}", Toast.LENGTH_SHORT)
                                        .show()

                                } else {
                                    Log.d("게시물 등록 에러 ", response.errorBody()!!.string())
                                    Toast.makeText(context, "게시물 등록 에러", Toast.LENGTH_SHORT).show()
                                }
                            }

                            override fun onFailure(call: Call<DataState>, t: Throwable) {
                                Log.e("게시물 등록 실패", t.localizedMessage)
                                Toast.makeText(context, "게시물 등록 실패", Toast.LENGTH_SHORT).show()
                            }
                        }
                        )
                }


                binding.delete.setOnClickListener {

                        service.delete(id).enqueue(object: Callback<DataState?> {
                            override fun onResponse(
                                call: Call<DataState?>,
                                response: Response<DataState?>
                            ) {
                                if (response.isSuccessful) {
                                    val result = response.body()
                                    Log.d("게시물", "${result?.code}")
                                    Log.d("게시물", "${result?.msg}")
                                    Toast.makeText(context, "${result?.msg}", Toast.LENGTH_SHORT).show()

                                } else {
                                    Log.d("게시물 등록 에러 ", response.errorBody()!!.string())
                                    Toast.makeText(context, "게시물 등록 에러", Toast.LENGTH_SHORT).show()
                                }
                            }
                            override fun onFailure(call: Call<DataState?>, t: Throwable) {
                                    Log.e("게시물 등록 실패", t.localizedMessage)
                                    Toast.makeText(context, "게시물 등록 실패", Toast.LENGTH_SHORT).show()
                                }
                         }
                       )
                    }

            return root
        }



  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
    }

}

