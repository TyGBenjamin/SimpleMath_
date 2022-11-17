package com.rave.simplemath.viewmodel.subtract

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rave.simplemath.model.repo.MathRepo
import com.rave.simplemath.view.subtract.SubtractActivityState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Subtract view model.
 *
 * @constructor Create empty Subtract view model
 */
class SubtractViewModel : ViewModel() {

    private val _subtractActivityState = MutableStateFlow(SubtractActivityState())
    val subtractActivityState get() = _subtractActivityState.asStateFlow()

    /**
     * Update num1.
     *
     * @param num
     */
    fun updateNum1(num: String) {
        _subtractActivityState.update { state -> state.copy(num1 = num) }
    }

    /**
     * Update num2.
     *
     * @param num
     */
    fun updateNum2(num: String) {
        _subtractActivityState.update { state -> state.copy(num2 = num) }
    }

    /**
     * Calculate difference.
     *
     */
    fun calculateDifference() {
        _subtractActivityState.update { state -> state.copy(isEvaluating = true) }
        viewModelScope.launch {
            val expr: String = _subtractActivityState.value.run { "$num1-$num2" }
            val result: String = MathRepo.evaluateExpression(expr)
            _subtractActivityState.update { state -> state.copy(isEvaluating = false, result = result) }
        }
    }
}
