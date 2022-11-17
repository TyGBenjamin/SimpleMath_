package com.rave.simplemath.view.multiply

import android.app.Activity
import android.content.Intent
import android.os.Bundle
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
import com.rave.simplemath.viewmodel.multiply.MultiplyViewModel

class MultiplyActivity : ComponentActivity() {

    private val multiplyViewModel by viewModels<MultiplyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleMathTheme {
                val state by multiplyViewModel.mutliplyState.collectAsState()
                Surface(
                    color = MaterialTheme.colorScheme.primary
                ) {
                    CalculationKeypad (
                        numInput1 = state.num1,
                        numInput2 = state.num2,
                        buttonText = getString(R.string.multiply),
                        numInput1Change = multiplyViewModel::firstNumber,
                        numInput2Change = multiplyViewModel::secondNumber,
                        enableButton = !state.isEvaluating,
                        buttonClicked = multiplyViewModel::multiplyNumbers
                    )
                    if (state.result.isNotEmpty()) {
                        //todo - understand what this is doing
                        val fullExpr = state.run { "$num1 * $num2 = $result" }
                        // because this isn't working
//                        val fullExpr = {"${state.num1} * ${state.num2} = ${state.result}"}

                        val resultIntent = Intent().putExtra(GetExprResult.RESULT, fullExpr)
                        setResult(Activity.RESULT_OK, resultIntent)
                        finish()
                    }
                }
            }
        }
    }

}