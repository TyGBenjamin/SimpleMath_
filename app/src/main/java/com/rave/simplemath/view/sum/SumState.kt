package com.rave.simplemath.view.sum

/**
 * Sum state.
 *
 * @property sum
 * @property isLoading
 * @property error
 * @constructor Create empty Sum state
 */
data class SumState(
    val sum: String = "",
    val isLoading: Boolean = false,
    val error: String = ""
)
