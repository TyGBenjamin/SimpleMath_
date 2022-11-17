package com.rave.simplemath.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rave.simplemath.model.repo.MathRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel: ViewModel() {

    val repo = MathRepo

    private val _equation: MutableStateFlow<Double> = MutableStateFlow(0.0)
    val equationState: StateFlow<Double> get() = _equation


    fun EvaluateExpression(expr: String) = viewModelScope.launch {
        _equation.value = repo.evaluateExpression(expr)
    }
}