package com.rave.simplemath.view.quotient

/**
 * Quotient state.
 *
 * @property isLoading
 * @property error
 * @property quotient
 * @constructor Create empty Quotient state
 */
data class QuotientState(
    val isLoading: Boolean = false,
    val error: String = "",
    val quotient: Double? = null
)
