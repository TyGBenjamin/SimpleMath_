package com.rave.simplemath.view.quotient

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
import com.rave.simplemath.viewmodel.QuotientVMFactory
import com.rave.simplemath.viewmodel.QuotientViewModel

/**
 * Quotient activity.
 *
 * @constructor Create empty Quotient activity
 */
class QuotientActivity : ComponentActivity() {
    private val quotientViewModel by viewModels<QuotientViewModel> { QuotientVMFactory() }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val quotientState by quotientViewModel.quotient.collectAsState()
            SimpleMathTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DisplayIfLoading(quotientState.isLoading)
                    val expr = EntryScreen(
                        buttonText = "Quotient",
                        buttonColor = Color.Green,
                        "/"
                    ) { firstArg, secondArg ->
                        quotientViewModel.getQuotient(firstArg, secondArg)
                    }
                    val quotient = quotientState.quotient
                    if (quotient != null) {
                        val expressionText = "$expr $quotient"

                        val resultIntent = Intent().putExtra(
                            DashboardActivity.EXPR_RESULT,
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
