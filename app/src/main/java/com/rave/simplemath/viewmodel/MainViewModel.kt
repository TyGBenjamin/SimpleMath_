package com.rave.simplemath.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Main view model holding and providing data for the view.
 *
 * @constructor Create empty Main view model
 */
class MainViewModel : ViewModel() {

    private val _result = MutableStateFlow("0")
    val result: StateFlow<String> get() = _result

    /**
     * Addmath operator function.
     *
     * @param x input number in first field
     * @param y input number in second field
     * @return
     */
    fun add(x: String, y: String): String {
        if (x != "" && y != "") {
            val newX = x.toInt()
            val newY = y.toInt()

            _result.value = (newX + newY).toString()
        }
        return _result.value
    }

    /**
     * Subtract math operator function.
     *
     * @param x
     * @param y
     * @return
     */
    fun subtract(x: String, y: String): String {
        if (x != "" && y != "") {
            val newX = x.toInt()
            val newY = y.toInt()
            if (newX >= newY) {
                _result.value = (newX - newY).toString()
            } else {
                _result.value = "This calculator only returns positive integers"
            }
        }
        return _result.value.toString()
    }

    /**
     * Multiply math operator function.
     *
     * @param x
     * @param y
     * @return
     */
    fun multiply(x: String, y: String): String {
        if (x != "" && y != "") {
            val newX = x.toInt()
            val newY = y.toInt()
            _result.value = (newX * newY).toString()
        }
        return _result.value
    }

    /**
     * Divide math operator function.
     *
     * @param x
     * @param y
     * @return
     */
    fun divide(x: String, y: String): String {
        if (x != "" && y != "") {
            val newX = x.toInt()
            val newY = y.toInt()
            if (newY != 0) {
                val long = (newX / newY).toLong()
                _result.value = long.toString()
            } else {
                _result.value = "Unable to Compute"
            }
        }
        return _result.value
    }

    /**
     * Clear method used to clear the field where the results from math operation are shown.
     *
     */
    fun clear() {
        _result.value = "0"
    }
}
