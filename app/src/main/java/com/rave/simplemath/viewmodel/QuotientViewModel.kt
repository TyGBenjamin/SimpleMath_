package com.rave.simplemath.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rave.simplemath.model.repo.MathRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QuotientViewModel: ViewModel() {

    val repo = MathRepo

    val _equation: MutableStateFlow<Double> = MutableStateFlow(0.0)
    val equationState: StateFlow<Double> get() = _equation


    fun EvaluateExpression(expr: String) = viewModelScope.launch {
        Log.e("%%%%%%%%%","${equationState.value}")
        _equation.value = repo.evaluateExpression(expr)
        Log.e("%%%%%%%%%","${equationState.value}")
    }
}