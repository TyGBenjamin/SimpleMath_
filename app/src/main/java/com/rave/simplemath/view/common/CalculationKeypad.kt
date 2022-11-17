package com.rave.simplemath.view.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

/**
 * Calculation keypad.
 *
 * @param numInput1
 * @param numInput2
 * @param buttonText
 * @param numInput1Change
 * @param numInput2Change
 * @param enableButton
 * @param buttonClicked
 * @receiver
 * @receiver
 * @receiver
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculationKeypad(
    numInput1: String,
    numInput2: String,
    buttonText: String,
    numInput1Change: (String) -> Unit = {},
    numInput2Change: (String) -> Unit = {},
    enableButton: Boolean = true,
    buttonClicked: () -> Unit = {}
) {
    Column() {
        Row(Modifier.weight(1f)) {
            TextField(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                textStyle = MaterialTheme.typography.titleLarge,
                value = numInput1,
                onValueChange = numInput1Change,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                placeholder = { Text(text = "0") }
            )
            TextField(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                textStyle = MaterialTheme.typography.titleLarge,
                value = numInput2,
                onValueChange = numInput2Change,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                placeholder = { Text(text = "0") }
            )
        }
        Button(
            onClick = buttonClicked,
            enabled = enableButton,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(20.dp)
        ) {
            Text(text = buttonText)
        }
    }
}
