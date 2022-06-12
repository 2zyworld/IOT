package com.example.myapplication.drawer.music

import android.annotation.SuppressLint
import android.os.Bundle
import android.system.Os.link
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.bottom.calender.DairyAdapter
import com.example.myapplication.bottom.calender.DairyData
import com.example.myapplication.databinding.FragmentMusicBinding
import com.example.myapplication.drawer.askhelp.datas.Document
import com.example.myapplication.drawer.music.data.musicdata
import com.example.myapplication.drawer.music.data.musicdataItem
import kotlinx.android.synthetic.main.fragment_askhelp_test.*
import kotlinx.android.synthetic.main.fragment_askhelp_test.view.*
import kotlinx.android.synthetic.main.fragment_music.*


class MusicFragment : Fragment() {

    private var _binding: FragmentMusicBinding? = null
    private val binding get() = _binding!!
    private var address:String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMusicBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }



    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.musiclist.layoutManager = LinearLayoutManager(context)

        MusicData.getMusicData {
            binding.musiclist.adapter = MusicAdapter(it, ::MusicFragmentdirections)

        }
    }
    private fun MusicFragmentdirections(musicdatalink: musicdataItem){

        address = musicdatalink.link.toString()
//        Log.d("address","${address}")
        binding.musicview.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }
        musicview.loadUrl("$address")

    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
