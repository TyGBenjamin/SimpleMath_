package com.rave.simplemath.viewmodel.sum

data class SumState(
    val num1: String = "",
    val num2: String = "",
    val result: String = "",
    val isEvaluating: Boolean = false
)