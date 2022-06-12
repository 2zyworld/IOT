package com.example.myapplication.bottom.dairy

import android.graphics.Color
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
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
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

            binding.piechart.setUsePercentValues(true)
            val entries = ArrayList<PieEntry>()
            entries.add(PieEntry(arguments?.getString("angry")!!.toFloat(), "화남"))
            entries.add(PieEntry(arguments?.getString("anticipation")!!.toFloat(), "기대"))
            entries.add(PieEntry(arguments?.getString("joy")!!.toFloat(), "기쁨"))
            entries.add(PieEntry(arguments?.getString("fear")!!.toFloat(), "두려움"))
            entries.add(PieEntry(arguments?.getString("disgust")!!.toFloat(), "싫음"))
            entries.add(PieEntry(arguments?.getString("sadness")!!.toFloat(), "슬픔"))
            entries.add(PieEntry(arguments?.getString("surprise")!!.toFloat(), "놀람"))
            entries.add(PieEntry(arguments?.getString("trust")!!.toFloat(), "신뢰"))


            val colorsItems = ArrayList<Int>()
            for (c in ColorTemplate.VORDIPLOM_COLORS) colorsItems.add(c)
            for (c in ColorTemplate.JOYFUL_COLORS) colorsItems.add(c)
            for (c in ColorTemplate.COLORFUL_COLORS) colorsItems.add(c)
            for (c in ColorTemplate.LIBERTY_COLORS) colorsItems.add(c)
            for (c in ColorTemplate.PASTEL_COLORS) colorsItems.add(c)

            colorsItems.add(ColorTemplate.getHoloBlue())


            val pieDataSet = PieDataSet(entries, "")
            pieDataSet.apply {
                colors = colorsItems
                valueTextColor = Color.BLACK
                valueTextSize = 16f

                val pieData = PieData(pieDataSet)
                binding.piechart.apply {
                    data = pieData
                    description.isEnabled = false
                    isRotationEnabled = true
                    centerText = ""
                    setEntryLabelColor(Color.BLACK)
                    animateY(1400, Easing.EaseInOutQuad)
                    animate()
                }

        }

//
//            binding.angryN.setText("화남 : ${arguments?.getString("angry")}%")
//            binding.anticipateN.setText("기대 : ${arguments?.getString("anticipation")}%")
//            binding.joyN.setText("기쁨 : ${arguments?.getString("joy")}%")
//            binding.fearN.setText("두려움 : ${arguments?.getString("fear")}%")
//            binding.disgustN.setText("싫음 : ${arguments?.getString("disgust")}%")
//            binding.sadnessN.setText("슬픔 : ${arguments?.getString("sadness")}%")
//            binding.surpriseN.setText("놀람 : ${arguments?.getString("surprise")}%")
//            binding.trustN.setText("신뢰 : ${arguments?.getString("trust")}%")


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