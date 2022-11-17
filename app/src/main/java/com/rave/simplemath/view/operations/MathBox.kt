package com.rave.simplemath.view.operations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MathBox(
    exprState: ExprState,
    operation: String,
    setFirstNum: (String) -> Unit,
    setSecNum: (String) -> Unit,
    evaluate: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = exprState.num1,
            onValueChange = { setFirstNum(it) }
        )
        TextField(
            value = exprState.num2,
            onValueChange = { setSecNum(it) }
        )
        Button(onClick = { evaluate() }) {
            Text(text = operation)
        }
    }
}
