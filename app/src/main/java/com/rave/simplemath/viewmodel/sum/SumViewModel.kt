package com.rave.simplemath.viewmodel.sum

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rave.simplemath.model.repo.MathRepo
import com.rave.simplemath.view.sum.SumActivityState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * This viewmodel holds data for sumactivty.
 *
 * @constructor Create empty Sum view model
 */
class SumViewModel : ViewModel() {

    private val _sumActivityState = MutableStateFlow(SumActivityState())
    val sumActivityState get() = _sumActivityState.asStateFlow()

    /**
     * Update num1.
     *
     * @param num
     */
    fun updateNum1(num: String) {
        _sumActivityState.update { state -> state.copy(num1 = num) }
    }

    /**
     * Update num2.
     *
     * @param num
     */
    fun updateNum2(num: String) {
        _sumActivityState.update { state -> state.copy(num2 = num) }
    }

    /**
     * Calculate sum.
     *
     */
    fun calculateSum() {
        _sumActivityState.update { state -> state.copy(isEvaluating = true) }
        viewModelScope.launch {
            val expr: String = _sumActivityState.value.run { "$num1+$num2" }
            val result: String = MathRepo.evaluateExpression(expr)
            _sumActivityState.update { state -> state.copy(isEvaluating = false, result = result) }
        }
    }
}
