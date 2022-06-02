package com.example.myapplication.drawer.askhelp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentAskhelpBinding
import com.example.myapplication.databinding.FragmentMusicBinding
import com.example.myapplication.drawer.askhelp.datas.Document
import com.example.myapplication.drawer.music.MusicViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker

class AskHelpFragment : Fragment() {

    private var _binding: FragmentAskhelpBinding? = null


    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAskhelpBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.hospital.layoutManager = LinearLayoutManager(context)
        AskHelpData.getAskHelpData("정신과") {
            binding.hospital.adapter = AskHelpAdapter(it.documents, ::AskHelpFragmentDirections)
        }

    }


    private fun AskHelpFragmentDirections(document:Document){


        findNavController().navigate(R.id.action_navigation_askhelp_to_askHelpMapFragment)
    }

}
