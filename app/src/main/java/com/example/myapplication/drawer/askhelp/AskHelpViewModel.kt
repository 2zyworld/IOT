package com.example.myapplication.drawer.askhelp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AskHelpViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is askhelp Fragment"
    }
    val text: LiveData<String> = _text
}