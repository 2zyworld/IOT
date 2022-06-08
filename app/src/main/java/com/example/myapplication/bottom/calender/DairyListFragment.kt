package com.example.myapplication.bottom.calender

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.bottom.calender.dairy_history.dairyhistoryItem
import com.example.myapplication.bottom.dairy.DairyViewModel
import com.example.myapplication.databinding.FragmentDairyListBinding
import com.example.myapplication.drawer.askhelp.datas.Document


class DairyListFragment : Fragment() {

    private var _binding: FragmentDairyListBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDairyListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.historyBoxList.layoutManager = LinearLayoutManager(context)

        DairyData.getDiaryData {
            binding.historyBoxList.adapter = DairyAdapter(it,::DairyListFragmentDirections)
//            Log.i("Diary", it.toString())
        }

    }



private fun DairyListFragmentDirections(dairyItem: dairyhistoryItem){

    val bundle = bundleOf("content" to dairyItem.content,"title" to dairyItem.title,"id" to dairyItem.id)

    findNavController().navigate(R.id.navigation_calender,bundle)

}


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

