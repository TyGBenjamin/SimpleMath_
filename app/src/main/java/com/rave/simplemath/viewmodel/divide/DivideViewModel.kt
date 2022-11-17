package com.rave.simplemath.viewmodel.divide

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rave.simplemath.model.repo.MathRepo
import com.rave.simplemath.view.divide.DivideActivityState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Divide view model.
 *
 * @constructor Create empty Divide view model
 */
class DivideViewModel : ViewModel() {
    private val _divideActivityState = MutableStateFlow(DivideActivityState())
    val divideActivityState get() = _divideActivityState.asStateFlow()

    /**
     * Update num1.
     *
     * @param num
     */
    fun updateNum1(num: String) {
        _divideActivityState.update { state -> state.copy(num1 = num) }
    }

    /**
     * Update num2.
     *
     * @param num
     */
    fun updateNum2(num: String) {
        _divideActivityState.update { state -> state.copy(num2 = num) }
    }

    /**
     * Calculate result.
     *
     */
    fun calculateResult() {
        _divideActivityState.update { state -> state.copy(isEvaluating = true) }
        viewModelScope.launch {
            val expr: String = _divideActivityState.value.run { "$num1/$num2" }
            val result: String = MathRepo.evaluateExpression(expr)
            _divideActivityState.update { state -> state.copy(isEvaluating = false, result = result) }
        }
    }
}
