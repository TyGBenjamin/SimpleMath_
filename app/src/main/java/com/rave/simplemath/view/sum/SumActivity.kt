package com.rave.simplemath.view.sum

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rave.simplemath.R
import com.rave.simplemath.ui.theme.SimpleMathTheme
import com.rave.simplemath.utils.GetExprResult
import com.rave.simplemath.view.common.CalculationKeypad
import com.rave.simplemath.view.dashboard.DashboardActivity
import com.rave.simplemath.viewmodel.SumViewModel

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
