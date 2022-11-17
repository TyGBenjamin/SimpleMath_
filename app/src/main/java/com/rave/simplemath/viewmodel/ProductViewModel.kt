package com.rave.simplemath.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.rave.simplemath.model.repo.MathRepo
import com.rave.simplemath.view.product.ProductState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Sum view model.
 *
 * @constructor Create empty Sum view model
 */
class ProductViewModel : ViewModel() {
    private val _product: MutableStateFlow<ProductState> = MutableStateFlow(ProductState())
    val product: StateFlow<ProductState> get() = _product

    /**
     * Get sum.
     *
     * @param firstArg
     * @param secondArg
     */
    fun getProduct(firstArg: String, secondArg: String) = viewModelScope.launch {
        _product.update { it.copy(isLoading = true) }
        val expression = "$firstArg*$secondArg"
        val productResponse = MathRepo.evaluateExpression(expression)
        _product.update { it.copy(isLoading = false, product = productResponse.toInt()) }
    }
}

/**
 * View model factory.
 *
 * @constructor Create empty V m factory
 */
class ProductVMFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductViewModel() as T
    }
}
