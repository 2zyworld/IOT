package com.example.myapplication.drawer.askhelp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentAskhelpBinding
import com.example.myapplication.databinding.FragmentAskhelpmainBinding

class AskHelpMainFragment() : Fragment() {

    private var _binding: FragmentAskhelpmainBinding? = null


    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAskhelpmainBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textView17.setOnClickListener {
            findNavController().navigate(R.id.navigation_askhelp)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}