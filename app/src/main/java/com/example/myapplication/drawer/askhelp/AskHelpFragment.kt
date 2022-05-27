package com.example.myapplication.drawer.askhelp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.FragmentAskhelpBinding
import com.example.myapplication.databinding.FragmentMusicBinding
import com.example.myapplication.drawer.music.MusicViewModel

class AskHelpFragment : Fragment() {

    private var _binding: FragmentAskhelpBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val AskHelpViewModel =
            ViewModelProvider(this).get(AskHelpViewModel::class.java)

        _binding = FragmentAskhelpBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }}
