package com.example.myapplication.bottom.dairy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.FragmentDairyDetailBinding


class DairyDetailFragment : Fragment() {

    private var _binding: FragmentDairyDetailBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val DairyViewModel =
            ViewModelProvider(this).get(DairyViewModel::class.java)
        _binding = FragmentDairyDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.dairy
//        DairyViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}