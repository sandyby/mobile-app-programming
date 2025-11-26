package com.example.lab_week_11_a_sandy.preferences

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class PreferenceViewModel(private val preferenceWrapper: PreferenceWrapper): ViewModel() {
    fun saveText(text: String){
        preferenceWrapper.saveText(text)
    }
    fun getText(): LiveData<String> {
        return preferenceWrapper.getText()
    }
}