package com.example.myapplication.drawer.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentHumidifierBinding

class HumidifierFragment : Fragment() {

    private var _binding: FragmentHumidifierBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHumidifierBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}