package com.rave.simplemath.view.product

/**
 * Product state.
 *
 * @property isLoading
 * @property error
 * @property product
 * @constructor Create empty Product state
 */
data class ProductState(
    val isLoading: Boolean = false,
    val error: String = "",
    val product: String = ""
)
