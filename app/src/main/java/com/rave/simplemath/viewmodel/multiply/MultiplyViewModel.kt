package com.rave.simplemath.viewmodel.multiply

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rave.simplemath.model.repo.MathRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Multiply view model.
 *
 * @constructor Create empty Multiply view model
 */
class MultiplyViewModel : ViewModel() {
    private val _multiplyState: MutableStateFlow<MultiplyState> = MutableStateFlow(MultiplyState())
    val mutliplyState: StateFlow<MultiplyState> get() = _multiplyState.asStateFlow()

    /**
     * First number.
     *
     * @param firstNum
     */
    fun firstNumber(firstNum: String) {
        _multiplyState.update { it.copy(num1 = firstNum) }
    }

    /**
     * Second number.
     *
     * @param secondNum
     */
    fun secondNumber(secondNum: String) {
        _multiplyState.update { state -> state.copy(num2 = secondNum) }
    }

    /**
     * Multiply numbers.
     *
     */
    fun multiplyNumbers() {
        _multiplyState.update { it.copy(isEvaluating = true) }
        viewModelScope.launch {
            val expr = _multiplyState.value.run { "$num1*$num2" }
            val result = MathRepo.evaluateExpression(expr)
            _multiplyState.update { it.copy(isEvaluating = false, result = result) }
        }
    }
}