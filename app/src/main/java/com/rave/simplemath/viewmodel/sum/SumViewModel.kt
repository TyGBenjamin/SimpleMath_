package com.rave.simplemath.viewmodel

import androidx.lifecycle.ViewModel
import com.rave.simplemath.viewmodel.sum.SumState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import androidx.lifecycle.viewModelScope
import com.rave.simplemath.model.repo.MathRepo
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


/**
 * Expression view model.
 *
 * @constructor Create empty Expression view model
 */
class SumViewModel(): ViewModel() {

    private val _sumState: MutableStateFlow<SumState> = MutableStateFlow(SumState())
    val sumState: StateFlow<SumState> get() = _sumState.asStateFlow()


    fun firstNumber(firstNum: String) {
        _sumState.update {
            it.copy(num1 = firstNum)
        }
    }
    fun secondNumber(secondNum: String) {
        _sumState.update {
            it.copy(num2 = secondNum)
        }
    }

    fun sumNumbers() {
        _sumState.update { state -> state.copy(isEvaluating = true) }
        viewModelScope.launch {
            val expr: String = _sumState.value.run {"$num1+$num2"}
            val result: String = MathRepo.evaluateExpression(expr)
            _sumState.update { it.copy(isEvaluating = false, result = result) }
        }
    }
}
