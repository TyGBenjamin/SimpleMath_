package com.rave.simplemath.view.difference

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
import com.rave.simplemath.viewmodel.DifferenceVMFactory
import com.rave.simplemath.viewmodel.DifferenceViewModel

/**
 * Difference activity.
 *
 * @constructor Create empty Difference activity
 */
class DifferenceActivity : ComponentActivity() {
    private val differenceViewModel by viewModels<DifferenceViewModel> { DifferenceVMFactory() }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val differenceState by differenceViewModel.difference.collectAsState()
            SimpleMathTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DisplayIfLoading(differenceState.isLoading)
                    val expr = EntryScreen("Difference", Color.Blue, "-") { text1, text2 ->
                        differenceViewModel.getDifference(text1, text2)
                    }
                    val difference = differenceState.difference
                    if (difference.isNotEmpty()) {
                        val expressionText = "$expr $difference"

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
