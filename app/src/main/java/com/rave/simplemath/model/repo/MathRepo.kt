package com.rave.simplemath.model.repo

import com.rave.simplemath.model.remote.MathService
import com.rave.simplemath.model.remote.RetrofitObject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

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
     * @param context the thread to be used
     * @return evaluation from mathematical expression
     */
    suspend fun evaluateExpression(expr: String, context: CoroutineDispatcher): String = withContext(context) {
        return@withContext service.evaluateExpression(expr)
    }
}
