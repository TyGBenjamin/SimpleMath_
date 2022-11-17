package com.rave.simplemath.model.repo

import com.rave.simplemath.model.remote.MathService
import com.rave.simplemath.model.remote.RetrofitObject

/**
 * Repository to evaluate math expressions.
 *
 * @constructor Create new [MathRepo]
 */
object MathRepo {
    private val service: MathService by lazy { RetrofitObject.getMathService() }

    /**
     * Evaluates mathematical expression passed in.
     *
     * @param expr mathematical expression to be evaluated
     * @return evaluation from mathematical expression
     */
    suspend fun evaluateExpression(expr: String): String {
//        val expressionResult = service.evaluateExpression(expr)
//        Log.e("EXPRESSION RESULT!", expressionResult)
//        return expressionResult
//        return service.evaluateExpression(expr)
        val servAnser = service.evaluateExpression(expr)
        return servAnser
    }
}
