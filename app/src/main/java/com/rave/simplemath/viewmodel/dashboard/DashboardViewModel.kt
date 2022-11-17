package com.rave.simplemath.viewmodel.dashboard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Dashboard view model.
 *
 * @constructor Create empty Dashboard view model
 */
class DashboardViewModel : ViewModel() {

    private val _dashboardState: MutableStateFlow<String> =
        MutableStateFlow("")
    val dashboardState: StateFlow<String> get() = _dashboardState

    /**
     * Get expression display result.
     *
     * @param dashboardDisplay
     */
    fun getExpressionDisplayResult(dashboardDisplay: String) {
        _dashboardState.value = dashboardDisplay
    }
}
