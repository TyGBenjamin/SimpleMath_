package com.rave.simplemath.util

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

/**
 * Get expr result.
 *
 * @constructor Create empty Get expr result
 */
class GetExprResult : ActivityResultContract<Intent, String>() {
    override fun createIntent(context: Context, input: Intent): Intent = input

    override fun parseResult(resultCode: Int, intent: Intent?): String {
        return intent?.getStringExtra(RESULT) ?: ""
    }

    companion object {
        const val RESULT = "ExprResult"
    }
}
