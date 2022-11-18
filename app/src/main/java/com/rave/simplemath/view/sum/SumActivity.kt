package com.rave.simplemath.view.sum

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.rave.simplemath.R
import com.rave.simplemath.model.remote.MathService
import com.rave.simplemath.model.repo.MathRepo
import com.rave.simplemath.ui.theme.SimpleMathTheme
import com.rave.simplemath.view.inputscreen.MathInputScreen
import com.rave.simplemath.viewmodel.SumActivityModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke

/**
 * Sum activity handles all addition operations.
 *
 * @constructor Create new instance of [SumActivity]
 */
class SumActivity() : ComponentActivity() {
    private val sumActivity by viewModels<SumActivityModel>()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SimpleMathTheme {
                val state by sumActivity.sumActivityState.collectAsState()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background )
                    {
                        val contextx = this
                    Column {
                        MathInputScreen(
                            num1 = state.num1,
                            num2 = state.num2,
                            buttonText = "Add",
                            input1change = { num -> sumActivity.updateNum1(num) },
                            input2change = { num -> sumActivity.updateNum2(num) }
                        )
                        Button(
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                            onClick = {
                                sumActivity.calculateSum()
                            }
                        )
                        {
                            Text(text = getString(R.string.sum))
                        }
                        if(state.result.isNotEmpty()) {
                            val resultMsg = state.run { "$result" }
                            val resultIntent_ = Intent().putExtra("calculatedSum", resultMsg)
                            setResult(RESULT_OK, resultIntent_)
                            finish()
                        }
                    }
                    }
                }
            }
        }
    }