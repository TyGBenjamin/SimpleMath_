package com.rave.simplemath.view.dashboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.rave.simplemath.R
import com.rave.simplemath.ui.theme.SimpleMathTheme
import com.rave.simplemath.view.difference.DifferenceActivity
import com.rave.simplemath.view.product.ProductActivity
import com.rave.simplemath.view.quotient.QuotientActivity
import com.rave.simplemath.view.sum.SumActivity

/**
 * Dashboard activity is the starting point of our SimpleMath Application.
 *
 * @constructor Create new instance of [DashboardActivity]
 */
class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleMathTheme {
                // A surface container using the 'background' color from the theme
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                0f to Color.Gray,
                                1000f to Color.White
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                  buttonFunction()
            }
        }
    }
}

@Composable
fun buttonFunction(){
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = {
                // Navigating to a new activity
                // 1) Create new Explicit Intent passing in the Activity class reference to navigate too
                val sumIntent = Intent(this@DashboardActivity, SumActivity::class.java)
                // 2) Pass the intent into the startActivity function
                startActivity(sumIntent)
            }
        ) {
            Text(getString(R.string.sum))
        }
        Button(
            onClick = {
                // Navigating to a new activity
                // 1) Create new Explicit Intent passing in the Activity class reference to navigate too
                val differenceIntent = Intent(this@DashboardActivity, DifferenceActivity::class.java)
                // 2) Pass the intent into the startActivity function
                startActivity(differenceIntent)
            }
        ) {
            Text(getString(R.string.difference))
        }
        Button(
            onClick = {
                // Navigating to a new activity
                // 1) Create new Explicit Intent passing in the Activity class reference to navigate too
                val productIntent = Intent(this@DashboardActivity, ProductActivity::class.java)
                // 2) Pass the intent into the startActivity function
                startActivity(productIntent)
            }
        ) {
            Text("product")
        }
        Button(
            onClick = {
                // Navigating to a new activity
                // 1) Create new Explicit Intent passing in the Activity class reference to navigate too
                val quotientIntent = Intent(this@DashboardActivity, QuotientActivity::class.java)
                // 2) Pass the intent into the startActivity function
                startActivity( quotientIntent)
            }
        ) {
            Text("quotient")
        }
    }
    }
    }
