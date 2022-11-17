package com.rave.simplemath.view.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rave.simplemath.R
import com.rave.simplemath.ui.theme.SimpleMathTheme
import com.rave.simplemath.utils.GetExprResult
import com.rave.simplemath.view.divide.DivideActivity
import com.rave.simplemath.view.multiply.MultiplyActivity
import com.rave.simplemath.view.subtraction.SubtractionActivity
import com.rave.simplemath.view.sum.SumActivity
import com.rave.simplemath.viewmodel.dashboard.DashboardViewModel

/**
 * Dashboard activity is the starting point of our SimpleMath Application.
 *
 * @constructor Create new instance of [DashboardActivity]
 */
class DashboardActivity : ComponentActivity() {

    private val dashboardViewModel by viewModels<DashboardViewModel>()

    private val exprResult: ActivityResultLauncher<Intent> =
        registerForActivityResult(GetExprResult()) { stringExpr ->
            if (stringExpr.isNotEmpty()) {
                dashboardViewModel.getExpressionDisplayResult(stringExpr)
                Toast.makeText(this, stringExpr, Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val resultString = intent.getStringExtra("result")
        val context = this
        fun startNewAct(sumClass: Class<*>) {
            val sumIntent = Intent(this, SumActivity::class.java)
            exprResult.launch(sumIntent)
        }
        setContent {
            SimpleMathTheme {
                val exprStringDesplay: String by dashboardViewModel.dashboardState.collectAsState()
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column() {
                        Box(
                            modifier = Modifier
                                .border(
                                    BorderStroke(2.dp, Color.LightGray)
                                )
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "${exprStringDesplay}",
                                fontSize = 64.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                        Button(
                            modifier = Modifier.size(200.dp, 150.dp),
                            onClick = {
                                // Navigating to a new activity
                                // 1)Create new Explicit Intent passing in the Activity class reference to navigate too
                                val sumIntent = Intent(context, SumActivity::class.java)

                                // 2) Start new activity with expect data comes back
                                exprResult.launch(sumIntent)
                            }
                        ) {
                            Text(getString(R.string.sum))
                        }
                        Button(
                            modifier = Modifier.size(200.dp, 150.dp),
                            onClick = {
                                val subractionIntent =
                                    Intent(context, SubtractionActivity::class.java)
                                exprResult.launch(subractionIntent)
                            }
                        ) {
                            Text(getString(R.string.subtraction))
                        }
                        Button(
                            modifier = Modifier.size(200.dp, 150.dp),
                            onClick = {
                                val multIntent = Intent(context, MultiplyActivity::class.java)
                                exprResult.launch(multIntent)
                            }
                        ) {
                            Text(getString(R.string.multiply))
                        }
                        Button(
                            modifier = Modifier.size(200.dp, 150.dp),
                            onClick = {
                                val multIntent = Intent(context, DivideActivity::class.java)
                                exprResult.launch(multIntent)
                            }
                        ) {
                            Text(getString(R.string.divide))
                        }
                    }
                }
            }
        }
    }
}
