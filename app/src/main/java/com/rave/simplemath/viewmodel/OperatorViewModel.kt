package com.rave.simplemath.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.rave.simplemath.model.repo.MathRepo
import com.rave.simplemath.view.operations.ExprState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Sum view model.
 *
 * @constructor Create [OperatorViewModel] instance
 */
class OperatorViewModel(
    private val operator: String
) : ViewModel() {
    private val repo = MathRepo

    private val _sumState: MutableStateFlow<ExprState> = MutableStateFlow(ExprState())
    val sumState: StateFlow<ExprState> get() = _sumState

    /**
     * Set first number.
     *
     * @param num string number input
     */
    fun setFirstNum(num: String) {
        _sumState.update { it.copy(num1 = num) }
    }

    /**
     * Set second number.
     *
     * @param num string number input
     */
    fun setSecNum(num: String) {
        _sumState.update { it.copy(num2 = num) }
    }

    /**
     * Evaluates sum by sending [ExprState] values to repo.
     *
     */
    fun evaluateSum() {
        viewModelScope.launch {
            if (sumState.value.num1 == "") _sumState.update { it.copy(num1 = "0") }
            if (sumState.value.num2 == "") _sumState.update { it.copy(num2 = "0") }
            with(sumState.value) {
                val result = repo.evaluateExpression("$num1$operator$num2")
                _sumState.update { it.copy(result = result) }
            }
        }
    }

    /**
     * Validates number input.
     *
     * @param input user input
     * @return true if all numbers
     */
    fun validateNumber(input: String): Boolean {
        return input.all { it.isDigit() } || input == ""
    }
}

/**
 * Operation view model factory to pass in operator.
 *
 * @property operator "+,-,*,/"
 * @constructor Constructs viewmodel with operator passed in
 */
class OperationViewModelFactory(
    private val operator: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OperatorViewModel(operator) as T
    }
}
