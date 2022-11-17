package com.rave.simplemath.view.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.rave.simplemath.R
import com.rave.simplemath.ui.theme.SimpleMathTheme
import com.rave.simplemath.view.operations.DivideActivity
import com.rave.simplemath.view.operations.MinusActivity
import com.rave.simplemath.view.operations.MultiplyActivity
import com.rave.simplemath.view.operations.SumActivity
import com.rave.simplemath.viewmodel.DashboardViewModel

/**
 * Dashboard activity is the starting point of our SimpleMath Application.
 *
 * @constructor Create new instance of [DashboardActivity]
 */
class DashboardActivity : ComponentActivity() {
    private val viewModel by viewModels<DashboardViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            // There are no request codes
            // viewmodelsetstate
            viewModel.setResult(result.data!!.getStringExtra("result")!!)
        }
        fun openActivityForResult(operationClass: Class<*>) {
            val intent: Intent = Intent(this, operationClass)
            activityResultLauncher.launch(intent)
        }
        setContent {
            SimpleMathTheme {
                val result: String by viewModel.result.collectAsState()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = result)
                        Button(
                            onClick = {
                                openActivityForResult(SumActivity::class.java)
                            }
                        ) {
                            Text(getString(R.string.sum))
                        }
                        Button(
                            onClick = {
                                openActivityForResult(MinusActivity::class.java)
                            }
                        ) {
                            Text(getString(R.string.subtract))
                        }
                        Button(
                            onClick = {
                                openActivityForResult(MultiplyActivity::class.java)
                            }
                        ) {
                            Text(getString(R.string.multiply))
                        }
                        Button(
                            onClick = {
                                openActivityForResult(DivideActivity::class.java)
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
