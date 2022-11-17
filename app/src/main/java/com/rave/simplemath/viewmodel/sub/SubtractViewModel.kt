package com.rave.simplemath.viewmodel.sub

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rave.simplemath.model.repo.MathRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Subtract [ViewModel] holding and providing data for the view.
 *
 * @constructor Create empty Main view model
 */
class SubtractViewModel : ViewModel() {
    private val repo = MathRepo

    private val _result = MutableStateFlow("0")
    val result: StateFlow<String> get() = _result

    /**
     * Evaluate sub expression.
     *
     * @param x
     * @param y
     */
    suspend fun evaluateSubExpression(x: String, y: String) {
        viewModelScope.launch {
            val mul: String = "$x-$y"
            _result.value = repo.evaluateExpression(mul)
            println("THIS IS VALUE OF FLOW ${repo.evaluateExpression(mul)}")
        }
    }
}
