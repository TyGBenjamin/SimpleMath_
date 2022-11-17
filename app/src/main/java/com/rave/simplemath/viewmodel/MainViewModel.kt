package com.rave.simplemath.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rave.simplemath.model.repo.MathRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Main view model holding and providing data for the view.
 *
 * @constructor Create empty Main view model
 */
class MainViewModel : ViewModel() {
    private val repo = MathRepo

    private val _result = MutableStateFlow("0")
    val result: StateFlow<String> get() = _result

    suspend fun evaluateAddExpression(expr:String,){
        viewModelScope.launch {
            _result.value = repo.evaluateExpression(expr)
            println("THIS IS VALUE OF FLOW ${repo.evaluateExpression(expr)}")
        }
    }

    suspend fun evaluateMulExpression(expr:String,){
        viewModelScope.launch {
            _result.value = repo.evaluateExpression(expr)
            println("THIS IS VALUE OF FLOW ${repo.evaluateExpression(expr)}")
        }
    }

    suspend fun evaluateSubExpression(expr:String,){
        viewModelScope.launch {


            _result.value = repo.evaluateExpression(expr)
            println("THIS IS VALUE OF FLOW ${repo.evaluateExpression(expr)}")
        }
    }

    suspend fun evaluateDivExpression(x:String, y:String,){
        viewModelScope.launch {
        }
            if (x != "" && y != "") {
                val newX = x.toInt()
                val newY = y.toInt()
                if (newY != 0) {
                    val long = (newX / newY).toLong()
                    val input = "$newX/newY"
                    _result.value = repo.evaluateExpression(input)
                } else {
                   var error = "Unable to Compute"
                }

                println("THIS IS VALUE OF FLOW ${repo.evaluateExpression("$newX/newY")}")
        }
    }

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
