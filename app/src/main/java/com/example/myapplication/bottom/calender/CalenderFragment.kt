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
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentCalenderBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

import com.example.myapplication.drawer.askhelp.AskHelpAdapter
import com.example.myapplication.drawer.askhelp.AskHelpData
private var title:String? =null
private var content:String? = null
private var date:String? = null

class CalenderFragment : Fragment() {

    private var _binding: FragmentCalenderBinding? = null
    private val binding get() = _binding!!
    private var id:Int? = null

    interface historyService {
        @PUT("post/{id}/")
        fun update(@Path("id") id: Int, @Body updateDate:updateDate): retrofit2.Call<DataState>
        @DELETE("post/{id}/")
        fun delete(@Path("id") id: Int): Call<DataState?>
    }
        data class updateDate(

            val author: String,
            val title: String,
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
            this.setText("${arguments?.getString("content")}")
        }
        binding.historytitle.apply{
            this.setText("${arguments?.getString("title")}")
        }
        id = arguments?.getInt("id")


            val retrofit = Retrofit.Builder()
                    .baseUrl("http://3.0.128.249:8000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            val service= retrofit.create(historyService::class.java)


            binding.modify.setOnClickListener {

                val title =binding.historytitle.text.toString()
                val content = binding.historybox.text.toString()
                val author = "iot"
                val Update = updateDate(author,title,content)

                    service.update(id!!, Update) .enqueue(object : Callback<DataState> {


                            override fun onResponse(
                                call: Call<DataState>,
                                response: Response<DataState>
                            ) {
                                Log.d("게시물에러","${Update}")
                                Log.d("게시물에러","${id}")


                                if (response.isSuccessful) {
                                    val result = response.body()
                                    Log.d("게시물", "${result?.code}")
                                    Log.d("게시물", "${result?.msg}")
                                    Toast.makeText(context, "수정 완료 되었습니다.", Toast.LENGTH_SHORT)
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

                 findNavController().navigate(R.id.dairyListFragment)
                }


                binding.delete.setOnClickListener {

                        service.delete(id!!).enqueue(object: Callback<DataState?> {
                            override fun onResponse(
                                call: Call<DataState?>,
                                response: Response<DataState?>
                            ) {
                                if (response.isSuccessful) {
                                    val result = response.body()
                                    Log.d("게시물", "${result?.code}")
                                    Log.d("게시물", "${result?.msg}")
                                    Toast.makeText(context, "삭제 완료 되었습니다.", Toast.LENGTH_SHORT).show()

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
                    findNavController().navigate(R.id.dairyListFragment)
                    }

            return root
        }



  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
    }

}

