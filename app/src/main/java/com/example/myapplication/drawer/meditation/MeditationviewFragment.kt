package com.example.myapplication.drawer.meditation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentMeditationViewBinding
import kotlinx.android.synthetic.main.fragment_meditation_view.*

class MeditationviewFragment : Fragment() {

    private var _binding: FragmentMeditationViewBinding? = null
    private val binding get() = _binding!!
    private var address:String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMeditationViewBinding.inflate(inflater, container, false)
        val root: View = binding.root
        address  =  arguments?.getString("address")

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        meditationview.apply{
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }

        meditationview.loadUrl("$address")


    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}