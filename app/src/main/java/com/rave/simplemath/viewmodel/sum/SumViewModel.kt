package com.rave.simplemath.viewmodel.sum

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rave.simplemath.model.repo.MathRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Sum [ViewModel] holding and providing data for the view.
 *
 * @constructor Create empty Main view model
 */
class SumViewModel : ViewModel() {
    private val repo = MathRepo

    private val _result = MutableStateFlow("0")
    val result: StateFlow<String> get() = _result

    /**
     * Evaluate add expression.
     *
     * @param x
     * @param y
     */
    suspend fun evaluateAddExpression(x: String, y: String) {
        viewModelScope.launch {
            val sum: String = "$x+$y"
            _result.value = repo.evaluateExpression(sum)
            println("THIS IS VALUE OF FLOW ${repo.evaluateExpression(sum)}")
        }
    }
}
