package com.example.lab_week_10_sandy.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
class TotalViewModel : ViewModel() {
    private val _total = MutableLiveData<Int>()
    private val _date = MutableLiveData<String>()
    val total: LiveData<Int> = _total
    val date: LiveData<String> = _date

    init {
        _total.postValue(0)
        _date.postValue("")
    }
    fun incrementTotal() {
        _total.postValue(_total.value?.plus(1))
        setDate(LocalDateTime.now().toString())
    }
    fun setTotal(newTotal: Int) {
        _total.postValue(newTotal)
    }

    fun setDate(newDate: String) {
        _date.postValue(newDate)
    }
}