package com.rave.simplemath.viewmodel

import com.rave.simplemath.utilTest.CoroutinesTestExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

internal class DashboardViewModelTest {

    @RegisterExtension
    private val extension = CoroutinesTestExtension()

    @Test
    @DisplayName("Testing result setter")
    fun testEvaluateExpression() = runTest(extension.testDispatcher) {
        val result = "12.0"
        val viewModel = DashboardViewModel()
        viewModel.setResult(result)

        Assertions.assertEquals(result, viewModel.result.value)
    }
}
