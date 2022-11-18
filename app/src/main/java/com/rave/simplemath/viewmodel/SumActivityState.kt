package com.rave.simplemath.viewmodel

data class SumActivityState(
                       val num1: String = "",
                       val num2: String = "",
                       val buttonText: String = "",
                       val isEvaluating: Boolean = false,
                       val result: String = "")
{

}