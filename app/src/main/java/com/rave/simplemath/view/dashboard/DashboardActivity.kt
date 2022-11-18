package com.rave.simplemath.view.dashboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import com.rave.simplemath.R
import com.rave.simplemath.ui.theme.SimpleMathTheme
import com.rave.simplemath.view.inputscreen.MathInputScreen
import com.rave.simplemath.view.sum.SumActivity
import com.rave.simplemath.viewmodel.SumActivityModel

/**
 * Dashboard activity is the starting point of our SimpleMath Application.
 *
 * @constructor Create new instance of [DashboardActivity]
 */
class DashboardActivity : ComponentActivity() {
    private val summary_ = SumActivityModel()
    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    {
    result: ActivityResult -> if (result.resultCode == Activity.RESULT_OK) {
        val intent: Intent? = result.data
        var calculatedResults = intent?.getStringExtra("calculatedSum") ?: ""
        Toast.makeText(this, calculatedResults, Toast.LENGTH_SHORT).show()
        summary_.updateFinalSum(calculatedResults)
    }

    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            SimpleMathTheme {
                val contextx = this
                val getSummary = summary_.finalSums.collectAsState()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Text(text = getSummary.value)
                        Row {
                            Button(
                                onClick = {
                                    val sumIntent = Intent(contextx, SumActivity::class.java)
                                    // 2) Pass the intent into the startActivity function
                                    //
                                    //startActivity(sumIntent)
                                    summary_.updateCalledOperation("1")
                                    startForResult.launch(sumIntent)
                                },
                                modifier = Modifier.weight(.5f)
                            )
                            {
                                Text("Add")
                            }
                            Button(
                                onClick = {
                                    // Navigating to a new activity
                                    // 1) Create new Explicit Intent passing in the Activity class reference to navigate too
                                    val sumIntent = Intent(contextx, SumActivity::class.java)
                                    // 2) Pass the intent into the startActivity function
                                    summary_.updateCalledOperation("2")
                                    startForResult.launch(sumIntent)
                                },
                                modifier = Modifier.weight(.5f)
                            ) {
                                Text("Subtract")
                            }
                        }
                        Row{
                            Button(
                            onClick = {
                                val sumIntent = Intent(contextx, SumActivity::class.java)
                                // 2) Pass the intent into the startActivity function
                                //startActivity(sumIntent)
                                summary_.updateCalledOperation("3")
                                startForResult.launch(sumIntent)
                            },
                            modifier = Modifier.weight(.5f)
                            )
                        {
                            Text("Divide")
                        }
                            Button(
                                onClick = {
                                    // Navigating to a new activity
                                    // 1) Create new Explicit Intent passing in the Activity class reference to navigate too
                                    val sumIntent = Intent(contextx, SumActivity::class.java)
                                    summary_.updateCalledOperation("4")
                                    startForResult.launch(sumIntent)
                                },
                                modifier = Modifier.weight(.5f)
                            ) {
                                Text("Multiply")
                            }
                        }
                    }

                }
            }
        }
    }
}
