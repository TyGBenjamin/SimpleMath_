package com.rave.simplemath.view.difference

/**
 * Difference state.
 *
 * @property difference
 * @property isLoading
 * @property error
 * @constructor Create empty Difference state
 */
data class DifferenceState(
    val difference: String = "",
    val isLoading: Boolean = false,
    val error: String = ""
)
