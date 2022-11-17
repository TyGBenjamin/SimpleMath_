package com.rave.simplemath.view.sum

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.rave.simplemath.R
import com.rave.simplemath.ui.theme.SimpleMathTheme
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
                    val context = LocalContext.current
                    DisplayIfLoading(sumState.isLoading)
                    var text by remember { mutableStateOf("0") }
                    var text2 by remember { mutableStateOf("0") }
                    Column() {
                        Row() {
                            Column() {
                                TextField(
                                    value = text,
                                    onValueChange = { newText ->
                                        text = newText.filter { c -> c.isDigit() }
                                    },
                                    label = { Text("Field 1") },
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Number
                                    )
                                )
                            }
                            Column() {
                                TextField(
                                    value = text2,
                                    onValueChange = { newText ->
                                        text2 = newText.filter { c -> c.isDigit() }
                                    },
                                    label = { Text("Field 2") },
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Number
                                    )
                                )
                            }
                        }
                        Row() {
                            Button(
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                                onClick = {
                                    sumViewModel.getSum(text.toInt(), text2.toInt())
                                }
                            ) { Text(text = getString(R.string.sum)) }
                        }
                        if (sumState.sum != null) {
                            val expressionText = "$text + $text2 = ${sumState.sum}"
                            Toast.makeText(context, expressionText, Toast.LENGTH_SHORT)
                                .show()
                            finish()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DisplayIfLoading(isLoading: Boolean) {
    if (isLoading) {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        )
    }
}
