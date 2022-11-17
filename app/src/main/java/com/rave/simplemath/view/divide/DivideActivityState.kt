package com.rave.simplemath.view.divide

/**
 * Divide activity state.
 *
 * @property num1
 * @property num2
 * @property result
 * @property isEvaluating
 * @constructor Create empty Divide activity state
 */
data class DivideActivityState(
    val num1: String = "",
    val num2: String = "",
    val result: String = "",
    val isEvaluating: Boolean = false
)
