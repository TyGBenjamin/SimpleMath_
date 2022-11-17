package com.rave.simplemath.view.multiply

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.rave.simplemath.R
import com.rave.simplemath.ui.theme.SimpleMathTheme
import com.rave.simplemath.util.GetExprResult
import com.rave.simplemath.view.common.CalculationKeypad
import com.rave.simplemath.viewmodel.multiply.MultiplyViewModel

/**
 * Multiply activity.
 *
 * @constructor Create empty Multiply activity
 */
class MultiplyActivity : ComponentActivity() {

    private val multiplyViewModel by viewModels<MultiplyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // val resultState by mainViewModel.result.collectAsState()
            SimpleMathTheme {
                val state by multiplyViewModel.multiplyActivityState.collectAsState()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculationKeypad(
                        numInput1 = state.num1,
                        numInput2 = state.num2,
                        buttonText = getString(R.string.multiply),
                        numInput1Change = multiplyViewModel::updateNum1,
                        numInput2Change = multiplyViewModel::updateNum2,
                        buttonClicked = multiplyViewModel::calculateProduct,
                        enableButton = !state.isEvaluating
                    )
                    if (state.result.isNotEmpty()) {
                        val resultMessage = state.run() { "$num1 * $num2 = $result" }
                        val resultIntent = Intent().putExtra(GetExprResult.RESULT, resultMessage)
                        setResult(RESULT_OK, resultIntent)
                        finish()
                    }
                }
            }
        }
    }
}
