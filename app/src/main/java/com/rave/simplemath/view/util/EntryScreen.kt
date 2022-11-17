package com.rave.simplemath.view.util

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryScreen(
    buttonText: String,
    buttonColor: Color,
    op: String,
    onClick: (String, String) -> Unit
): String {
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
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                onClick = { onClick(text, text2) }
            ) { Text(text = buttonText) }
        }
    }
    return "$text $op $text2 ="
}
