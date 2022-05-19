package com.example.myapplication.bottom.dairy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DairyViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "일기작성 페이지"
    }
    val text: LiveData<String> = _text
}