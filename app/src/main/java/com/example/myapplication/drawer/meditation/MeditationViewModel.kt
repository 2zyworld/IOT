package com.example.myapplication.drawer.meditation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MeditationViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Meditation Fragment"
    }
    val text: LiveData<String> = _text
}