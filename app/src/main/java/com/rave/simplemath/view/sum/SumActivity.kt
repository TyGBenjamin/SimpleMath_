package com.rave.simplemath.view.sum

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.rave.simplemath.R
import com.rave.simplemath.ui.theme.SimpleMathTheme
import com.rave.simplemath.utils.GetExprResult
import com.rave.simplemath.view.common.CalculationKeypad
import com.rave.simplemath.viewmodel.sum.SumViewModel

/**
 * Sum activity handles all addition operations.
 *
 * @constructor Create new instance of [SumActivity]
 */
@OptIn(ExperimentalMaterial3Api::class)
class SumActivity : ComponentActivity() {
    private val sumViewModel by viewModels<SumViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleMathTheme {
                val state by sumViewModel.sumState.collectAsState()
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculationKeypad(
                        numInput1 = state.num1,
                        numInput2 = state.num2,
                        buttonText = getString(R.string.sum),
                        numInput1Change = { num -> sumViewModel.firstNumber(num) },
                        numInput2Change = sumViewModel::secondNumber,
                        buttonClicked = sumViewModel::sumNumbers,
                        enableButton = !state.isEvaluating
                    )

                    if (state.result.isNotEmpty()) {
                        val fullExpr = state.run { "$num1 + $num2 = $result" }
                        val resultIntent = Intent().putExtra(GetExprResult.RESULT, fullExpr)
                        setResult(Activity.RESULT_OK, resultIntent)
                        finish()
                    }
                }
            }
        }
    }
}
