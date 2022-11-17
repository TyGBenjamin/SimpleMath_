package com.rave.simplemath.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.rave.simplemath.model.repo.MathRepo
import com.rave.simplemath.view.difference.DifferenceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Sum view model.
 *
 * @constructor Create empty Sum view model
 */
class DifferenceViewModel : ViewModel() {
    private val _difference: MutableStateFlow<DifferenceState> = MutableStateFlow(DifferenceState())
    val difference: StateFlow<DifferenceState> get() = _difference

    /**
     * Get sum.
     *
     * @param firstArg
     * @param secondArg
     */
    fun getDifference(firstArg: String, secondArg: String) = viewModelScope.launch {
        _difference.update { it.copy(isLoading = true) }
        val expression = "$firstArg-$secondArg"
        val differenceResponse = MathRepo.evaluateExpression(expression, Dispatchers.IO)
        _difference.update { it.copy(isLoading = false, difference = differenceResponse) }
    }
}

/**
 * View model factory.
 *
 * @constructor Create empty V m factory
 */
class DifferenceVMFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DifferenceViewModel() as T
    }
}
