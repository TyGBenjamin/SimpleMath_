package com.rave.simplemath.viewmodel.dashboard

import androidx.lifecycle.ViewModel
import com.rave.simplemath.view.dashboard.DashboardActivityState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Dashboard view model.
 *
 * @constructor Create empty Dashboard view model
 */
class DashboardViewModel : ViewModel() {
    private val _expression = MutableStateFlow(DashboardActivityState())
    val expression get() = _expression.asStateFlow()

    /**
     * Set expression.
     *
     * @param expr
     */
    fun setExpression(expr: String) {
        _expression.update { state -> state.copy(expression = expr) }
    }
}
