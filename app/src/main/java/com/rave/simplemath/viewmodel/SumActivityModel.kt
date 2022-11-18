package com.rave.simplemath.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rave.simplemath.model.repo.MathRepo
import com.rave.simplemath.view.inputscreen.MathInputScreen
import com.rave.simplemath.view.sum.SumActivity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SumActivityModel: ViewModel() {

    private val _sumActivityState = MutableStateFlow(SumActivityState())
    val sumActivityState get() = _sumActivityState.asStateFlow()



    private val _sums: MutableStateFlow<String> = MutableStateFlow("")
    val finalSums: StateFlow<String> get() = _sums

    private val _operation: MutableStateFlow<String> = MutableStateFlow("+")
    val selectedOperation: StateFlow<String> get() = _operation

    fun updateCalledOperation(opCall: String)
    {
        _operation.value = opCall
    }
    fun updateFinalSum(num: String){
        _sums.value = num
    }

    fun updateNum1(num: String){
        _sumActivityState.update{ state -> state.copy(num1 = num) }
    }

    fun updateNum2(num: String){
        _sumActivityState.update{ state -> state.copy(num2 = num) }
    }

    fun calculateSum()
    {
        val opCall = _operation.value
        viewModelScope.launch{
            val expr: String = _sumActivityState.value.run{ "$num1$opCall$num2" }
            println(expr)
            val result: String = MathRepo.evaluateExpression(expr).toString()
            _sumActivityState.update{ state -> state.copy(isEvaluating = false, result = result) }
        }
    }

}