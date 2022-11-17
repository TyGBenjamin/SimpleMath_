package com.rave.simplemath.view.sum

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.rave.simplemath.ui.theme.SimpleMathTheme
import com.rave.simplemath.view.dashboard.DashboardActivity
import com.rave.simplemath.view.util.DisplayIfLoading
import com.rave.simplemath.view.util.EntryScreen
import com.rave.simplemath.viewmodel.SumVMFactory
import com.rave.simplemath.viewmodel.SumViewModel

/**
 * Sum activity handles all addition operations.
 *
 * @constructor Create new instance of [SumActivity]
 */
class SumActivity : ComponentActivity() {
    private val sumViewModel by viewModels<SumViewModel> { SumVMFactory() }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val sumState by sumViewModel.sum.collectAsState()
            SimpleMathTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DisplayIfLoading(sumState.isLoading)
                    val expr = EntryScreen(buttonText = "Sum", buttonColor = Color.Red, "+") {
                            firstArg, secondArg ->
                        sumViewModel.getSum(firstArg, secondArg)
                    }
                    val sum = sumState.sum
                    if (sum.isNotEmpty()) {
                        val expressionText = "$expr $sum"

                        val resultIntent = Intent().putExtra(
                            DashboardActivity.RESULT,
                            expressionText
                        )
                        setResult(RESULT_OK, resultIntent)
                        finish()
                    }
                }
            }
        }
    }
}
