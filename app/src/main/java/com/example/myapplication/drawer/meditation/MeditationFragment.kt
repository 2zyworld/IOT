package com.example.myapplication.drawer.meditation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMeditationBinding
import com.example.myapplication.drawer.meditation.data.meditationdataItem
import com.example.myapplication.drawer.music.MusicAdapter
import com.example.myapplication.drawer.music.MusicData
import com.example.myapplication.drawer.music.data.musicdataItem
import kotlinx.android.synthetic.main.fragment_meditation_view.*
import kotlinx.android.synthetic.main.fragment_music.*


class MeditationFragment : Fragment() {

    private var _binding: FragmentMeditationBinding? = null
    private val binding get() = _binding!!
    private var address:String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMeditationBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }



    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.meditationlist.layoutManager = LinearLayoutManager(context)

        MeditationData.getMeditationData {
            binding.meditationlist.adapter = MeditationAdapter(it, ::MeditationFragmentdirections)

        }
    }
    private fun MeditationFragmentdirections(meditationdatalink: meditationdataItem){

        val bundle = bundleOf("address" to meditationdatalink.link)

        findNavController().navigate(R.id.meditationviewFragment,bundle)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
