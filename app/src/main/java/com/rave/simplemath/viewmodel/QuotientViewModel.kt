package com.rave.simplemath.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.rave.simplemath.model.repo.MathRepo
import com.rave.simplemath.view.quotient.QuotientState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Sum view model.
 *
 * @constructor Create empty Sum view model
 */
class QuotientViewModel : ViewModel() {
    private val _quotient: MutableStateFlow<QuotientState> = MutableStateFlow(QuotientState())
    val quotient: StateFlow<QuotientState> get() = _quotient

    /**
     * Get sum.
     *
     * @param firstArg
     * @param secondArg
     */
    fun getQuotient(firstArg: String, secondArg: String) = viewModelScope.launch {
        _quotient.update { it.copy(isLoading = true) }
        val expression = "$firstArg%2F$secondArg"
        val quotientResponse = MathRepo.evaluateExpression(expression)
        _quotient.update { it.copy(isLoading = false, quotient = quotientResponse) }
    }
}

/**
 * View model factory.
 *
 * @constructor Create empty V m factory
 */
class QuotientVMFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QuotientViewModel() as T
    }
}
