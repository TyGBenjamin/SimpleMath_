package com.rave.simplemath.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rave.simplemath.model.repo.MathRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Main view model holding and providing data for the view.
 *
 * @constructor Create empty Main view model
 */
class MainViewModel : ViewModel() {
    private val repo = MathRepo

    private val _result = MutableStateFlow("0")
    val result: StateFlow<String> get() = _result

    /**
     * Evaluate add expression for addition operations.
     *
     * @param x
     * @param y
     */
    suspend fun evaluateAddExpression(x: String, y: String) {
        viewModelScope.launch {
            val sum: String = "$x%2B$y"
            _result.value = repo.evaluateExpression(sum)
            println("THIS IS VALUE OF FLOW ${repo.evaluateExpression(sum)}")
        }
    }

    /**
     * Evaluate mul expression for multiplication operations.
     *
     * @param x
     * @param y
     */
    suspend fun evaluateMulExpression(x: String, y: String) {
        viewModelScope.launch {
            val result: String = "$x*$y"
            _result.value = repo.evaluateExpression(result)
            println("THIS IS VALUE OF FLOW ${repo.evaluateExpression(result)}")
        }
    }

    /**
     * Evaluate sub expression.
     *
     * @param x
     * @param y
     */
    suspend fun evaluateSubExpression(x: String, y: String) {
        viewModelScope.launch {
            val subtract = "$x-$y"
            _result.value = repo.evaluateExpression(subtract)
            println("THIS IS VALUE OF FLOW ${repo.evaluateExpression(subtract)}")
        }
    }
}
