package com.example.myapplication.drawer.meditation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.FragmentMeditationBinding


class MeditationFragment : Fragment() {

    private var _binding: FragmentMeditationBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val MeditationViewModel =
            ViewModelProvider(this).get(MeditationViewModel::class.java)

        _binding = FragmentMeditationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.meditation
        MeditationViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }}
