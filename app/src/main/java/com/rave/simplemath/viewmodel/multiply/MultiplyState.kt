package com.rave.simplemath.viewmodel.multiply

/**
 * Multiply state.
 *
 * @property num1
 * @property num2
 * @property result
 * @property isEvaluating
 * @constructor Create empty Multiply state
 */
data class MultiplyState(
    val num1: String = "",
    val num2: String = "",
    val result: String = "",
    val isEvaluating: Boolean = false
)
