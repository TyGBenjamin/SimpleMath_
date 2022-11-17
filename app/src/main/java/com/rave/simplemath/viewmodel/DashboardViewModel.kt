package com.rave.simplemath.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DashboardViewModel: ViewModel() {

    private val _result: MutableStateFlow<String> = MutableStateFlow("")
    val result: StateFlow<String> get() = _result

    fun setResult(result: String){
        _result.value = result
    }
}