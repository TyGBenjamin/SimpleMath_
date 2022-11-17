package com.rave.simplemath.view.multiply

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
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
import com.rave.simplemath.viewmodel.mul.MultiplyViewModel
import kotlinx.coroutines.launch

/**
 * Multiply activity handles all addition operations.
 *
 * @constructor Create new instance of [MultiplyActivity]
 */
class MultiplyActivity : ComponentActivity() {
    val viewModel = MultiplyViewModel()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current

//            fun setResult(s:String, s2: String) = setResult(RESULT_OK,Intent().putExtra("Testing", "s s2"))
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
                        Column(modifier = Modifier.wrapContentSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {
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
                            val bundle = Bundle()
                            // passing the data into the bundle
                            bundle.putString("key1", "$value,$value2")
                            var mulstring = viewModel.result.collectAsState().value

                            Button(
                                onClick = {
//                                    val addSum = viewModel.add(x=value, y = value2)
                                    lifecycleScope.launch {
                                        var sumAnswer =
                                            viewModel.evaluateMultiplyExpression(x = value, y = value2)
                                        println("ANSWER FROM API IS $sumAnswer")
                                    }

                                    println("HEREEEEE is and $value $value2")


                                    Toast.makeText(
                                        context,
                                        "$value + $value2 =" +
                                                "$mulstring",
                                        Toast.LENGTH_SHORT
                                    ).show()
//                                    finish()
                                }
                            ) {
                                Text(text = "Calculate")
                                println(" SUMSTRING : $mulstring")
                            }
                            Button(onClick = { finish() }) {
                                Text( text = "MainScreen")
                                val result = setResult(
                                    ComponentActivity.RESULT_OK, Intent()
                                        .putExtra("Testing",mulstring )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
