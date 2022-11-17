package com.rave.simplemath.viewmodel.subtraction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rave.simplemath.model.repo.MathRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Subtraction view model.
 *
 * @constructor Create empty Subtraction view model
 */
class SubtractionViewModel : ViewModel() {
    private val _subtractionState: MutableStateFlow<SubtractionState> = MutableStateFlow(
        SubtractionState()
    )
    val subtractionState: StateFlow<SubtractionState> get() = _subtractionState.asStateFlow()

    /**
     * First number.
     *
     * @param firstNum
     */
    fun firstNumber(firstNum: String) {
        _subtractionState.update {
            it.copy(num1 = firstNum)
        }
    }

    /**
     * Second number.
     *
     * @param secondNum
     */
    fun secondNumber(secondNum: String) {
        _subtractionState.update {
            it.copy(num2 = secondNum)
        }
    }

    /**
     * Subtact numbers.
     *
     */
    fun subtactNumbers() {
        _subtractionState.update { state -> state.copy(isEvaluating = true) }
        viewModelScope.launch {
            val expr: String = _subtractionState.value.run { "$num1-$num2" }
            val result: String = MathRepo.evaluateExpression(expr)
            _subtractionState.update { state -> state.copy(isEvaluating = false, result = result) }
        }
    }
}
