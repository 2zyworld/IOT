package com.example.myapplication.drawer.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RoomViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is room Fragment"
    }
    val text: LiveData<String> = _text
}