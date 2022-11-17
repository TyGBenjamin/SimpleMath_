package com.rave.simplemath.view.sum

/**
 * Sum activity state.
 *
 * @property num1
 * @property num2
 * @property result
 * @property isEvaluating
 * @constructor Create empty Sum activity state
 */
data class SumActivityState(
    val num1: String = "",
    val num2: String = "",
    val result: String = "",
    val isEvaluating: Boolean = false
)
