package com.rave.simplemath.view.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.rave.simplemath.view.sum.SumActivity
import com.rave.simplemath.view.sum.SumState
import com.rave.simplemath.viewmodel.DashboardViewModel
import com.rave.simplemath.viewmodel.SumViewModel

/**
 * Dashboard activity is the starting point of our SimpleMath Application.
 *
 * @constructor Create new instance of [DashboardActivity]
 */
class DashboardActivity : ComponentActivity() {
    private val viewModel by viewModels<DashboardViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            // There are no request codes
            //viewmodelsetstate
            viewModel.setResult(result.data!!.getStringExtra("result")!!)
        }
        fun openSecondActivityForResult() {
            val intent: Intent = Intent(this, SumActivity::class.java)
            activityResultLauncher.launch(intent)
        }
        setContent {

            SimpleMathTheme {
                val result: String by viewModel.result.collectAsState()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = result)
                        Button(
                            onClick = {
                                openSecondActivityForResult()
                            }
                        ) {
                            Text(getString(R.string.sum))
                        }
                    }
                }
            }
        }


    }




}
