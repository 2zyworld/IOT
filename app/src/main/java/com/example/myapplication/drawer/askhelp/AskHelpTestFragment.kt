package com.example.myapplication.drawer.askhelp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentAskhelpTestBinding
import com.example.myapplication.databinding.FragmentAskhelpmainBinding
import kotlinx.android.synthetic.main.fragment_askhelp_test.*

class AskHelpTestFragment() : Fragment() {

    private var _binding: FragmentAskhelpTestBinding? = null


    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAskhelpTestBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        webview.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }

        webview.loadUrl("https://blutouch.net/selftest/depress")
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}