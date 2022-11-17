package com.rave.simplemath.viewmodel.div

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rave.simplemath.model.repo.MathRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Divide [ViewModel] holding and providing data for the view.
 *
 * @constructor Create empty Divide view model for handling division queries
 */
class DivideViewModel : ViewModel() {
    private val repo = MathRepo

    private val _result = MutableStateFlow("0")
    val result: StateFlow<String> get() = _result

    /**
     * Evaluate div expression for division operations.
     *
     * @param x
     * @param y
     */
    suspend fun evaluateDivExpression(x: String, y: String) {
        viewModelScope.launch {
            if (y.toInt() != 0) {
                val mul: String = "$x/$y"
                _result.value = repo.evaluateExpression(mul)
                println("THIS IS VALUE OF FLOW ${repo.evaluateExpression(mul)}")
            }
        }
    }
}
