package com.rave.simplemath.viewmodel

import com.rave.simplemath.model.repo.MathRepo
import com.rave.simplemath.utilTest.CoroutinesTestExtension
import io.mockk.coEvery
import io.mockk.mockkObject
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

internal class OperatorViewModelTest {

    @RegisterExtension
    private val extension = CoroutinesTestExtension()

    private val num1 = 2
    private val num2 = 4

    @BeforeEach
    fun beforeEach() {
        mockkObject(MathRepo)
    }

    @Test
    @DisplayName("Testing all expressions can be evaluated. Also test number setters")
    fun testEvaluateExpression() = runTest(extension.testDispatcher) {
        // Given
        val operatorList = listOf("+", "-", "*", "/")
        var result: String
        var expr: String
        var viewModel: OperatorViewModel
        var testResult: String
        for (operator in operatorList) {
            result = when (operator) {
                "+" -> num1 + num2
                "-" -> num1 - num2
                "*" -> num1 * num2
                "/" -> num1 / num2
                else -> 0
            }.toDouble().toString()

            expr = "$num1$operator$num2"
            coEvery { MathRepo.evaluateExpression(expr) } coAnswers { result }

            // When
            viewModel = OperatorViewModel(operator)
            viewModel.setFirstNum(num1.toString())
            viewModel.setSecNum(num2.toString())
            viewModel.evaluateSum()
            testResult = viewModel.sumState.value.result

            // Then
            Assertions.assertEquals(result, testResult)
        }
    }

    @Test
    @DisplayName("Test validator")
    fun testValidator() = runTest(extension.testDispatcher) {
        val viewModel = OperatorViewModel("+")

        Assertions.assertTrue(viewModel.validateNumber("14255"))
        Assertions.assertTrue(viewModel.validateNumber(""))
        Assertions.assertFalse(viewModel.validateNumber("1.0"))
        Assertions.assertFalse(viewModel.validateNumber("-1"))
        Assertions.assertFalse(viewModel.validateNumber("dfsa"))
    }
}
