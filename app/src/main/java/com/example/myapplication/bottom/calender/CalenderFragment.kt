package com.example.myapplication.bottom.calender

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentCalenderBinding
import com.example.myapplication.drawer.askhelp.AskHelpAdapter
import com.example.myapplication.drawer.askhelp.AskHelpData
private var title:String? =null
private var content:String? = null
private var date:String? = null

class CalenderFragment : Fragment() {

    private var _binding: FragmentCalenderBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCalenderBinding.inflate(inflater, container, false)
        val root: View = binding.root


//        arguments?.let {
//            title = it.getString("title")
//            content = it.getString("content")
//            date = it.getString("date")
//
//        }
//        Log.i("argument", "${title}, ${content}, ${date}")

        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}