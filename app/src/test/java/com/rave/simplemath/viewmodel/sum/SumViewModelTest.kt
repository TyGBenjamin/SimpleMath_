package com.rave.simplemath.viewmodel.sum

import com.rave.simplemath.model.repo.MathRepo
import com.rave.simplemath.utilTest.CoroutinesTestExtension
import com.rave.simplemath.view.sum.SumActivityState
import io.mockk.coEvery
import io.mockk.mockkObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

internal class SumViewModelTest {

    @RegisterExtension
    private val coroutinesTestExtension = CoroutinesTestExtension()
    private val viewModel = SumViewModel()

    @BeforeEach
    fun setUp() {
        mockkObject(MathRepo)
    }

    @Test
    @DisplayName("Testing that num1 state is updated when new number is passed in")
    fun updateNum1() {
        // Given
        val newNum: String = "123"

        // When
        viewModel.updateNum1(newNum)

        // Then
        Assertions.assertEquals(newNum, viewModel.sumActivityState.value.num1)
    }

    @Test
    @DisplayName("Testing that num2 state is updated when new number is passed in")
    fun updateNum2() {
        // Given
        val newNum: String = "123"

        // When
        viewModel.updateNum2(newNum)

        // Then
        Assertions.assertEquals(newNum, viewModel.sumActivityState.value.num2)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun calculateSum() = runTest(coroutinesTestExtension.testDispatcher) {
        // Given
        val num1 = "123"
        val num2 = "321"
        val result = "444"
        val stateUpdates = mutableListOf<SumActivityState>()
        viewModel.updateNum1(num1)
        viewModel.updateNum2(num2)
        coEvery { MathRepo.evaluateExpression("$num1+$num2") } coAnswers { result }

        val job = launch {
            viewModel.sumActivityState.toList(stateUpdates)
        }

        // When
        viewModel.calculateSum()

        // Then
        Assertions.assertEquals(result, stateUpdates.last().result)
        job.cancel()
    }
}
