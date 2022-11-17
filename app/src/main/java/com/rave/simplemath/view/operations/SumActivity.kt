package com.rave.simplemath.view.operations

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.rave.simplemath.R
import com.rave.simplemath.ui.theme.SimpleMathTheme
import com.rave.simplemath.viewmodel.OperationViewModelFactory
import com.rave.simplemath.viewmodel.OperatorViewModel

/**
 * Sum activity handles all addition operations.
 *
 * @constructor Create new instance of [SumActivity]
 */
class SumActivity : ComponentActivity() {
    // private val viewModel by viewModels<SumViewModel>()
    private val viewModel by viewModels<OperatorViewModel> {
        OperationViewModelFactory("+")
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleMathTheme {
                val sumState: ExprState by viewModel.sumState.collectAsState()
                if (sumState.result != "") {
                    val intent: Intent = Intent()
                    intent.putExtra("result", sumState.result)
                    setResult(RESULT_OK, intent)
                    finish()
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MathBox(
                        sumState,
                        stringResource(R.string.sum),
                        viewModel::setFirstNum,
                        viewModel::setSecNum,
                        viewModel::evaluateSum
                    )
                }
            }
        }
    }
}
