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
        return service.evaluateExpression(expr)
    }
}
