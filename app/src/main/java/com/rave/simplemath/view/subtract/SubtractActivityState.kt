package com.rave.simplemath.view.subtract

/**
 * Subtract activity state.
 *
 * @property num1
 * @property num2
 * @property result
 * @property isEvaluating
 * @constructor Create empty Subtract activity state
 */
data class SubtractActivityState(
    val num1: String = "",
    val num2: String = "",
    val result: String = "",
    val isEvaluating: Boolean = false
)
