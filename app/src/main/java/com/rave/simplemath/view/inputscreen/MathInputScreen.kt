package com.rave.simplemath.view.inputscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@ExperimentalMaterial3Api
@Composable
fun MathInputScreen(
    num1: String = "",
    num2: String = ",",
    buttonText: String = "",
    input1change: (String) -> Unit,
    input2change: (String) -> Unit,
    ) {

    //enableButton: Boolean = true,
    //buttonClicked: () -> Unit = {}
    // Declare Input Variables

    Box {
        Row {
            Column {
                Text(text = "Input1")
                TextField(value = num1, onValueChange = input1change)
            }
            Column {
                Text(text = "Input2")
                TextField(value = num2, onValueChange = input2change)
            }
        }
    }
}
