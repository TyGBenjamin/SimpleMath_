package com.rave.simplemath.view.multiply

/**
 * Multiply activity state.
 *
 * @property num1
 * @property num2
 * @property result
 * @property isEvaluating
 * @constructor Create empty Multiply activity state
 */
data class MultiplyActivityState(
    val num1: String = "",
    val num2: String = "",
    val result: String = "",
    val isEvaluating: Boolean = false
)
