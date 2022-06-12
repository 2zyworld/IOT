package com.example.myapplication.bottom.dairy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.graphics.toColorInt
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.bottom.calender.CalenderFragment
import com.example.myapplication.bottom.calender.DiaryService
import com.example.myapplication.bottom.calender.dairy_history.dairyhistory
import com.example.myapplication.databinding.FragmentDairyDetailBinding
import kotlinx.android.synthetic.main.fragment_dairy_detail.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path


class DairyDetailFragment : Fragment() {

    private var _binding: FragmentDairyDetailBinding? = null
    private val binding get() = _binding!!
    private var color:String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDairyDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root




            color = arguments?.getString("colorstate")
            binding.colorvalue.setTextColor("#${color}".toColorInt())




            binding.angryN.setText("화남 : ${arguments?.getString("angry")}%")
            binding.anticipateN.setText("기대 : ${arguments?.getString("anticipation")}%")
            binding.joyN.setText("기쁨 : ${arguments?.getString("joy")}%")
            binding.fearN.setText("두려움 : ${arguments?.getString("fear")}%")
            binding.disgustN.setText("싫음 : ${arguments?.getString("disgust")}%")
            binding.sadnessN.setText("슬픔 : ${arguments?.getString("sadness")}%")
            binding.surpriseN.setText("놀람 : ${arguments?.getString("surprise")}%")
            binding.trustN.setText("신뢰 : ${arguments?.getString("trust")}%")


//            Log.d("colorname","${color}")



        return root
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.askhelpb.setOnClickListener {
            findNavController().navigate(R.id.askHelpMainFragment)
        }
        binding.musicb.setOnClickListener {
            findNavController().navigate(R.id.musicFragment)
        }
        binding.roomb.setOnClickListener {
            findNavController().navigate(R.id.navigation_room)
        }
        binding.meditationb.setOnClickListener {
            findNavController().navigate(R.id.navigation_meditation)
        }

       binding.emotionB.setOnClickListener{
            emotionvalue.visibility = View.VISIBLE
            emotionB.visibility = View.GONE
        }

        binding.emotionvalue.setOnClickListener{
            emotionvalue.visibility = View.GONE
            emotionB.visibility = View.VISIBLE
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}