package com.rave.simplemath.view.operations

/**
 * Data class to track [ExprState].
 *
 * @property num1 first number
 * @property num2 second number
 * @property result result of sum of nums
 * @constructor Create empty Sum state
 */
data class ExprState(
    val num1: String = "",
    val num2: String = "",
    val result: String = ""
)
