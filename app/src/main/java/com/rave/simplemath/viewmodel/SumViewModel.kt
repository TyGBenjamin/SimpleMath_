package com.rave.simplemath.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rave.simplemath.model.repo.MathRepo
import com.rave.simplemath.view.sum.SumState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SumViewModel: ViewModel() {
    private val repo = MathRepo

    private val _sumState: MutableStateFlow<SumState> = MutableStateFlow(SumState())
    val sumState: StateFlow<SumState> get() = _sumState

    fun setFirstNum(num: String){
        _sumState.update { it.copy(num1 = num) }
    }

    fun setSecNum(num: String) {
        _sumState.update { it.copy(num2 = num) }
    }

    fun evaluateSum() {
        with(sumState.value) {
            if(num1=="") num1 = "0"
            if(num2=="") num2 = "0"
            viewModelScope.launch {
                val result = repo.evaluateExpression("$num1+$num2")
                _sumState.update { it.copy(result=result.toString()) }
            }
        }
    }

    fun validateNumber(input: String): Boolean {
        return input.all { it.isDigit() }
    }
}