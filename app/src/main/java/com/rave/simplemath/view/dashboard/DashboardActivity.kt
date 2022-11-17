package com.rave.simplemath.view.dashboard

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rave.simplemath.ui.theme.SimpleMathTheme
import com.rave.simplemath.util.GetExprResult
import com.rave.simplemath.view.divide.DivideActivity
import com.rave.simplemath.view.multiply.MultiplyActivity
import com.rave.simplemath.view.subtract.SubtractActivity
import com.rave.simplemath.view.sum.SumActivity
import com.rave.simplemath.viewmodel.dashboard.DashboardViewModel

/**
 * Dashboard activity is the starting point of our SimpleMath Application.
 *
 * @constructor Create new instance of [DashboardActivity]
 */
class DashboardActivity : ComponentActivity() {

    private val dashboardViewModel by viewModels<DashboardViewModel>()

    private val exprResult: ActivityResultLauncher<Intent> = registerForActivityResult(GetExprResult()) { fullExpr ->
        if (fullExpr.isNotEmpty()) {
            Toast.makeText(this, fullExpr, Toast.LENGTH_SHORT).show()
            dashboardViewModel.setExpression(fullExpr)
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
                    val sumIntent = Intent(this, SumActivity::class.java)
                    val subtractIntent = Intent(this, SubtractActivity::class.java)
                    val multiplyIntent = Intent(this, MultiplyActivity::class.java)
                    val divideIntent = Intent(this, DivideActivity::class.java)
                    val state by dashboardViewModel.expression.collectAsState()
                    MainScreen(
                        sumIntent = sumIntent,
                        subtractIntent = subtractIntent,
                        multiplyIntent = multiplyIntent,
                        divideIntent = divideIntent,
                        state = state,
                        exprResult = exprResult
                    )
                }
            }
        }
    }
}

@Suppress("LongParameterList")
@Composable
fun MainScreen(
    sumIntent: Intent,
    subtractIntent: Intent,
    multiplyIntent: Intent,
    divideIntent: Intent,
    state: DashboardActivityState,
    exprResult: ActivityResultLauncher<Intent>
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = state.expression,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 60.dp),
            fontSize = 40.sp,
            maxLines = 2
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                modifier = Modifier.requiredSize(150.dp),
                onClick = { exprResult.launch(sumIntent) }
            ) { Text("Sum") }
            Button(
                modifier = Modifier.requiredSize(150.dp),
                onClick = { exprResult.launch(subtractIntent) }
            ) { Text("Subtract") }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                modifier = Modifier.requiredSize(150.dp),
                onClick = { exprResult.launch(multiplyIntent) }
            ) { Text("Multiply") }
            Button(
                modifier = Modifier.requiredSize(150.dp),
                onClick = { exprResult.launch(divideIntent) }
            ) { Text("Divide") }
        }
    }
}
