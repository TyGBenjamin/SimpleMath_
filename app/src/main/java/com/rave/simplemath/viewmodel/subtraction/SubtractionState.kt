package com.rave.simplemath.viewmodel.subtraction

/**
 * Subtraction state.
 *
 * @property num1
 * @property num2
 * @property result
 * @property isEvaluating
 * @constructor Create empty Subtraction state
 */
data class SubtractionState(
    val num1: String = "4",
    val num2: String = "2",
    val result: String = "",
    val isEvaluating: Boolean = false
)
