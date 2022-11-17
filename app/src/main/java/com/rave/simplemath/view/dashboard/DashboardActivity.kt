package com.rave.simplemath.view.dashboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rave.simplemath.ui.theme.SimpleMathTheme
import com.rave.simplemath.view.divide.DivideActivity
import com.rave.simplemath.view.multiply.MultiplyActivity
import com.rave.simplemath.view.subtract.SubtractActivity
import com.rave.simplemath.view.sum.SumActivity
import com.rave.simplemath.viewmodel.MainViewModel

/**
 * Dashboard activity is the starting point of our SimpleMath Application.
 *
 * @constructor Create new instance of [DashboardActivity]
 */
class DashboardActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel = MainViewModel()
        super.onCreate(savedInstanceState)
        setContent {
            SimpleMathTheme {
                var displayResult: String by remember {
                    mutableStateOf("")
                }
                val launcher =
                    rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                        if (it.resultCode == Activity.RESULT_OK) {
                            println("THIS IS ${it.data?.getStringExtra("Testing")}")
                            displayResult = it.data?.getStringExtra("Testing")!!
                        }
                    }
                val context = LocalContext.current
                // A surface container using the 'background' color from the theme
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(modifier = Modifier.padding(vertical = 10.dp)) {
                        MainScreen(displayResult)
                        Row(modifier = Modifier.padding(vertical = 10.dp)) {
                            Button(
                                onClick = {
                                    // Navigating to a new activity
                                    // 1) Create new Explicit Intent passing in the Activity
                                    // class reference to navigate too
                                    val sumIntent = Intent(context, SumActivity::class.java)
                                    // 2) Pass the intent into the startActivity function
//                                    startActivity(sumIntent)
                                    launcher.launch(sumIntent)
                                }
                            ) {
                                Text(text = "+", fontSize = 25.sp)
                            }
                            Button(
                                onClick = {
                                    // Navigating to a new activity
                                    // 1) Create new Explicit Intent passing in the Activity
                                    // class reference to navigate too
                                    val mulIntent = Intent(context, MultiplyActivity::class.java)
                                    // 2) Pass the intent into the startActivity function
//                                    startActivity(mulIntent
                                    launcher.launch(mulIntent)
                                }
                            ) {
                                Text(text = "x", fontSize = 25.sp)
                            }

                            Button(
                                onClick = {
                                    // Navigating to a new activity
                                    // 1) Create new Explicit Intent passing in the Activity
                                    // class reference to navigate too
                                    val divIntent = Intent(context, DivideActivity::class.java)
                                    // 2) Pass the intent into the startActivity function
                                    launcher.launch(divIntent)
                                }
                            ) {
                                Text(text = "÷", fontSize = 25.sp)
                            }
                            Button(
                                onClick = {
                                    // Navigating to a new activity
                                    // 1) Create new Explicit Intent passing in the Activity
                                    // class reference to navigate too
                                    val subIntent = Intent(context, SubtractActivity::class.java)
                                    // 2) Pass the intent into the startActivity function

                                    launcher.launch(subIntent)
                                }
                            ) {
                                Text(text = "-", fontSize = 25.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(result:String) {
    var value: String by remember {
        mutableStateOf("")
    }

    Box(modifier = Modifier.wrapContentHeight(), contentAlignment = Alignment.Center) {
        Text(text = "Simple Calculator", textAlign = TextAlign.Center)
        Text(text = "Result shown below", textAlign = TextAlign.Center)
        TextField(value = value, onValueChange = {
            println(it)
            value = it
        }, label = { Label(result) })
    }
}

@Composable
fun Label(result:String = "Show Result") {
    Text(
        text = result
    )
}

@Composable
fun Label2() {
    Text(
        text = "input number",
        fontSize = 8.sp
    )
}
