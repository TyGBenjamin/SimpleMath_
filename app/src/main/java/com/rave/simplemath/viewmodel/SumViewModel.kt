package com.rave.simplemath.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.rave.simplemath.model.repo.MathRepo
import com.rave.simplemath.view.sum.SumState
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
class SumViewModel : ViewModel() {
    private val _sum: MutableStateFlow<SumState> = MutableStateFlow(SumState())
    val sum: StateFlow<SumState> get() = _sum

    /**
     * Get sum.
     *
     * @param firstArg
     * @param secondArg
     */
    fun getSum(firstArg: String, secondArg: String) = viewModelScope.launch {
        _sum.update { it.copy(isLoading = true) }
        val expression = "$firstArg%2B$secondArg"
        val sumResponse = MathRepo.evaluateExpression(expression, Dispatchers.IO)
        _sum.update { it.copy(isLoading = false, sum = sumResponse.toInt()) }
    }
}

/**
 * View model factory.
 *
 * @constructor Create empty V m factory
 */
class SumVMFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SumViewModel() as T
    }
}
