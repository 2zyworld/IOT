package com.example.myapplication.bottom.home



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_calender.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
//
//        calendarView.minDate = SimpleDateFormat("yyyyMMdd").parse("20220101").time
//        calendarView.maxDate = SimpleDateFormat("yyyyMMDD").parse("21001231").time
//
//        textViewCal.text = ""+calendarView.date
//        calendarView.setOnDateChangeListener{ view, year, month, dayOfMonth ->
//
//            if(month<9){
//                if(dayOfMonth<10){
//
//                }
//            }
//
//        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeQustion.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_dairyFragment2)

        binding.calendarView.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_dairyDetailFragment)
        }
        }
    }
//    btnStartDate.setOnClickListener {
//        val today = GregorianCalendar()
//        val year: Int = today.get(Calendar.YEAR)
//        val month: Int = today.get(Calendar.MONTH)
//        val date: Int = today.get(Calendar.DATE)
//        val dlg = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
//            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
//                etStartDate.setText("${year}년 ${month+1}월 ${dayOfMonth}일")
//            }
//        }, year, month, date)
//        dlg.show()
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}