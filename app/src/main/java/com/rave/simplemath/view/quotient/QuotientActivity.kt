package com.rave.simplemath.view.quotient

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.rave.simplemath.R
import com.rave.simplemath.ui.theme.SimpleMathTheme
import com.rave.simplemath.viewmodel.DifferenceViewModel
import com.rave.simplemath.viewmodel.QuotientViewModel
import com.rave.simplemath.viewmodel.SumViewModel

class QuotientActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val quotientViewModel: QuotientViewModel by viewModels()
        setContent {
            val equationState = quotientViewModel.equationState.collectAsState().value
            if(equationState!=0.0){
                var result = equationState
                var expr = "$result"//"${textState.value} / ${secondTextState.value} = ${result}"
                val resultIntent = Intent().putExtra( "expr result", expr)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
            SimpleMathTheme {
                // A surface container using the 'background' color from the theme
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                0f to Color.Black,
                                1000f to Color.White
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    QuotientScreen(quotientViewModel, equationState)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuotientScreen(quotientViewModel: QuotientViewModel, equationState: Double){
    val activity = LocalContext.current as? Activity

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        val textState = remember { mutableStateOf("") }
        val secondTextState = remember { mutableStateOf("") }
        Row(horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.size(100.dp)
            )
            TextField(
                value = secondTextState.value,
                onValueChange = { secondTextState.value = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.size(100.dp)
            )
        }

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            onClick = {
                quotientViewModel.EvaluateExpression("${textState.value}/${secondTextState.value}")

            }
        ) {
            Text(text = "Evaluate Quotient")
        }
        Text(text = "${equationState}")
    }
}