package com.rave.simplemath.view.product

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
import androidx.compose.ui.graphics.Color
import com.rave.simplemath.ui.theme.SimpleMathTheme
import com.rave.simplemath.view.dashboard.DashboardActivity
import com.rave.simplemath.view.util.DisplayIfLoading
import com.rave.simplemath.view.util.EntryScreen
import com.rave.simplemath.viewmodel.ProductVMFactory
import com.rave.simplemath.viewmodel.ProductViewModel

/**
 * Product activity.
 *
 * @constructor Create empty Product activity
 */
class ProductActivity : ComponentActivity() {
    private val productViewModel by viewModels<ProductViewModel> { ProductVMFactory() }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val productState by productViewModel.product.collectAsState()
            SimpleMathTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DisplayIfLoading(productState.isLoading)
                    val expr = EntryScreen("Product", Color.Cyan, "*") { text1, text2 ->
                        productViewModel.getProduct(text1, text2)
                    }
                    val product = productState.product
                    if (product.isNotEmpty()) {
                        val expressionText = "$expr $product"

                        val resultIntent = Intent().putExtra(
                            DashboardActivity.RESULT,
                            expressionText
                        )
                        setResult(RESULT_OK, resultIntent)
                        finish()
                    }
                }
            }
        }
    }
}
