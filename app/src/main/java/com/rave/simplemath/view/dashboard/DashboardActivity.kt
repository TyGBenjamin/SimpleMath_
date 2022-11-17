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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
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
    val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            // Handle the Intent
            val exprResult = intent?.getStringExtra(EXPR_RESULT) ?: ""
            Toast.makeText(this, exprResult, Toast.LENGTH_SHORT).show()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleMathTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = this
                    Column() {
                        Button(
                            onClick = {
                                // Navigating to a new activity
                                // 1) Create new Explicit Intent passing in the Activity class reference
                                // to navigate too
                                val sumIntent = Intent(context, SumActivity::class.java)
                                // 2) Pass the intent into the startActivity function
                                startForResult.launch(sumIntent)
                            }
                        ) {
                            Text(getString(R.string.sum))
                        }
                        Button(
                            onClick = {
                                val differenceIntent = Intent(
                                    context,
                                    DifferenceActivity::class.java
                                )
                                startForResult.launch(differenceIntent)
                            }
                        ) {
                            Text("Difference")
                        }
                        Button(
                            onClick = {
                                val productIntent = Intent(
                                    context,
                                    ProductActivity::class.java
                                )
                                startForResult.launch(productIntent)
                            }
                        ) {
                            Text("Product")
                        }
                        Button(
                            onClick = {
                                val quotientIntent = Intent(
                                    context,
                                    QuotientActivity::class.java
                                )
                                startForResult.launch(quotientIntent)
                            }
                        ) {
                            Text("Quotient")
                        }
                    }
                }
            }
        }
    }
    companion object {
        const val EXPR_RESULT = "ExprResult"
    }
}
