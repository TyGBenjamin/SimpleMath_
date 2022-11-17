package com.rave.simplemath.viewmodel.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rave.simplemath.model.repo.MathRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Dashboard view model.
 *
 * @property repo
 * @constructor Create empty Dashboard view model
 */
class DashboardViewModel() : ViewModel() {

    private val _dashboardState: MutableStateFlow<String> =
        MutableStateFlow("")
    val dashboardState: StateFlow<String> get() = _dashboardState

    /**
     * Get expression display result.
     *
     * @param expr
     */
    fun getExpressionDisplayResult(dashboardDisplay: String) {
        _dashboardState.value = dashboardDisplay
    }
}
