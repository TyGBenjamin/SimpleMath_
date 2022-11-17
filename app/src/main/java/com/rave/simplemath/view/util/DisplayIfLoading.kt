package com.rave.simplemath.view.util

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DisplayIfLoading(isLoading: Boolean) {
    if (isLoading) {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        )
    }
}
