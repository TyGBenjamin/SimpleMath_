package com.rave.simplemath.view.subtract

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.lifecycleScope
import com.rave.simplemath.ui.theme.SimpleMathTheme
import com.rave.simplemath.view.dashboard.Label2
import com.rave.simplemath.viewmodel.sub.SubtractViewModel
import kotlinx.coroutines.launch

/**
 * Sum activity handles all addition operations.
 *
 * @constructor Create new instance of [SumActivity]
 */
class SubtractActivity : ComponentActivity() {
    val viewModel = SubtractViewModel()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            SimpleMathTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var value: String by remember {
                        mutableStateOf("")
                    }

                    var value2: String by remember {
                        mutableStateOf("")
                    }

                    Box(
                        modifier = Modifier.wrapContentHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column {
                            Text(
                                text = "Result shown below",
                                textAlign = TextAlign.Center
                            )
                            TextField(
                                value = value,
                                onValueChange = {
                                    println(it)
                                    value = it
                                },
                                label = { Label2() },
                                modifier = Modifier.wrapContentSize()
                            )
                            TextField(
                                value = value2,
                                onValueChange = {
                                    println(it)
                                    value2 = it
                                },
                                label = { Label2() },
                                modifier = Modifier.wrapContentSize()
                            )
                            var substring = viewModel.result.collectAsState().value
                            Button(
                                onClick = {
//                                    val addSum = viewModel.add(x=value, y = value2)
                                    lifecycleScope.launch {
                                        viewModel.evaluateSubExpression(x = value, y = value2)
                                    }
                                    val result = (value.toInt()- value2.toInt()).toString()
                                    Toast.makeText(
                                        context,
                                        "$value - $value2 = $result",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            ) {
                                Text(text = "Calculate")
                                substring = viewModel.result.collectAsState().value
                            }
                            Button(onClick = { finish() }) {
                                Text(text = "MainScreen")
                                val result = setResult(
                                    ComponentActivity.RESULT_OK, Intent()
                                        .putExtra("Testing", substring)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

