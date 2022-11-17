package com.rave.simplemath.viewmodel.divide

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rave.simplemath.model.repo.MathRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Divide view model.
 *
 * @constructor Create empty Divide view model
 */
class DivideViewModel : ViewModel() {
    private val _divideState: MutableStateFlow<DivideState> = MutableStateFlow(DivideState())
    val divideState: StateFlow<DivideState> get() = _divideState.asStateFlow()

    /**
     * First number.
     *
     * @param firstNum
     */
    fun firstNumber(firstNum: String) {
        _divideState.update { it.copy(num1 = firstNum) }
    }

    /**
     * Second number.
     *
     * @param secondNum
     */
    fun secondNumber(secondNum: String) {
        _divideState.update { state -> state.copy(num2 = secondNum) }
    }

    /**
     * Divide numbers.
     *
     */
    fun divideNumbers() {
        _divideState.update { it.copy(isEvaluating = true) }
        viewModelScope.launch {
            val expr = _divideState.value.run { "$num1/$num2" }
            val result = MathRepo.evaluateExpression(expr)
            _divideState.update { it.copy(isEvaluating = false, result = result) }
        }
    }
}
