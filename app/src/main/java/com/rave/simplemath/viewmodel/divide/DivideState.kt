package com.rave.simplemath.viewmodel.divide

/**
 * Divide state.
 *
 * @property num1
 * @property num2
 * @property result
 * @property isEvaluating
 * @constructor Create empty Divide state
 */
data class DivideState(
    val num1: String = "",
    val num2: String = "",
    val result: String = "",
    val isEvaluating: Boolean = false
)
