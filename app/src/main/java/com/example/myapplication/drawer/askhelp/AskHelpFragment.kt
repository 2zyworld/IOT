package com.example.myapplication.drawer.askhelp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentAskhelpBinding
import com.example.myapplication.drawer.askhelp.datas.Document

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

        val bundle = bundleOf("location" to document.x,"locationy" to document.y)

        findNavController().navigate(R.id.askHelpMapFragment,bundle)

    }

}
