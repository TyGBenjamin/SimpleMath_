package com.rave.simplemath.view.subtraction

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.rave.simplemath.R
import com.rave.simplemath.ui.theme.SimpleMathTheme
import com.rave.simplemath.utils.GetExprResult
import com.rave.simplemath.view.common.CalculationKeypad
import com.rave.simplemath.viewmodel.subtraction.SubtractionViewModel

class SubtractionActivity : ComponentActivity() {
    private val subtractionViewModel by viewModels<SubtractionViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleMathTheme {
                val state by subtractionViewModel.subtractionState.collectAsState()
                Surface(
//                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.tertiary
                ) {
                    CalculationKeypad(
                        numInput1 = state.num1,
                        numInput2 = state.num2,
                        buttonText = getString(R.string.subtraction),
                        numInput1Change = { num -> subtractionViewModel.firstNumber(num) },
                        numInput2Change = subtractionViewModel::secondNumber,
                        buttonClicked = subtractionViewModel::subtactNumbers,
                        enableButton = !state.isEvaluating
                    )
                    if (state.result.isNotEmpty()) {
                        val fullExpr = state.run { "$num1 - $num2 = $result" }
                        val resultIntent = Intent().putExtra(GetExprResult.RESULT, fullExpr)
                        setResult(Activity.RESULT_OK, resultIntent)
                        finish()
                    }
                }
            }
        }
    }
}
