package com.rave.simplemath.viewmodel.multiply

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rave.simplemath.model.repo.MathRepo
import com.rave.simplemath.view.multiply.MultiplyActivityState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Multiply view model.
 *
 * @constructor Create empty Multiply view model
 */
class MultiplyViewModel : ViewModel() {
    private val _multiplyActivityState = MutableStateFlow(MultiplyActivityState())
    val multiplyActivityState get() = _multiplyActivityState.asStateFlow()

    /**
     * Update num1.
     *
     * @param num
     */
    fun updateNum1(num: String) {
        _multiplyActivityState.update { state -> state.copy(num1 = num) }
    }

    /**
     * Update num2.
     *
     * @param num
     */
    fun updateNum2(num: String) {
        _multiplyActivityState.update { state -> state.copy(num2 = num) }
    }

    /**
     * Calculate product.
     *
     */
    fun calculateProduct() {
        _multiplyActivityState.update { state -> state.copy(isEvaluating = true) }
        viewModelScope.launch {
            val expr: String = _multiplyActivityState.value.run { "$num1*$num2" }
            val result: String = MathRepo.evaluateExpression(expr)
            _multiplyActivityState.update { state -> state.copy(isEvaluating = false, result = result) }
        }
    }
}
