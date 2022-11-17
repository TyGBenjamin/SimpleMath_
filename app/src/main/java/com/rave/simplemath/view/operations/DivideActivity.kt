package com.rave.simplemath.view.operations

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.rave.simplemath.R
import com.rave.simplemath.ui.theme.SimpleMathTheme
import com.rave.simplemath.viewmodel.OperationViewModelFactory
import com.rave.simplemath.viewmodel.OperatorViewModel

/**
 * Sum activity handles all minus operations.
 *
 * @constructor Create new instance of [MinusActivity]
 */
class DivideActivity : ComponentActivity() {
    // private val viewModel by viewModels<SumViewModel>()
    private val viewModel by viewModels<OperatorViewModel> {
        OperationViewModelFactory("/")
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleMathTheme {
                val sumState: ExprState by viewModel.sumState.collectAsState()
                if (sumState.result != "") {
                    val intent: Intent = Intent()
                    intent.putExtra("result", sumState.result)
                    setResult(RESULT_OK, intent)
                    finish()
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TextField(
                            value = sumState.num1,
                            onValueChange = {
                                if (viewModel.validateNumber(it)) { viewModel.setFirstNum(it) }
                            }
                        )
                        TextField(
                            value = sumState.num2,
                            onValueChange = {
                                if (viewModel.validateNumber(it)) { viewModel.setSecNum(it) }
                            }
                        )
                        Button(onClick = { viewModel.evaluateSum() }) {
                            Text(text = getString(R.string.divide))
                        }
                    }
                }
            }
        }
    }
}
