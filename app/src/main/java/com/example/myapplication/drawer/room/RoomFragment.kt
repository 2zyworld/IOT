package com.example.myapplication.drawer.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.FragmentRoomBinding


class RoomFragment : Fragment() {

    private var _binding: FragmentRoomBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val RoomViewModel =
            ViewModelProvider(this).get(RoomViewModel::class.java)

        _binding = FragmentRoomBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.room
        RoomViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
