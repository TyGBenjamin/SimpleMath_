package com.rave.simplemath.view.divide

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
import com.rave.simplemath.viewmodel.divide.DivideViewModel

class DivideActivity: ComponentActivity() {

    private val divideViewModel by viewModels<DivideViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleMathTheme {
                val state by divideViewModel.divideState.collectAsState()
                Surface(
                    color = MaterialTheme.colorScheme.inversePrimary
                ) {
                    CalculationKeypad (
                        numInput1 = state.num1,
                        numInput2 = state.num2,
                        buttonText = getString(R.string.multiply),
                        numInput1Change = divideViewModel::firstNumber,
                        numInput2Change = divideViewModel::secondNumber,
                        enableButton = !state.isEvaluating,
                        buttonClicked = divideViewModel::divideNumbers
                    )
                    if (state.result.isNotEmpty()) {
                        val fullExpr = state.run { "$num1 / $num2 = $result" }

                        val resultIntent = Intent().putExtra(GetExprResult.RESULT, fullExpr)
                        setResult(RESULT_OK, resultIntent)
                        finish()
                    }
                }
            }
        }
    }

}