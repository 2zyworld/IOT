package com.example.myapplication.drawer.music

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.FragmentMusicBinding


class MusicFragment : Fragment() {

    private var _binding: FragmentMusicBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val MusicViewModel =
            ViewModelProvider(this).get(MusicViewModel::class.java)

        _binding = FragmentMusicBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.music
        MusicViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }}
