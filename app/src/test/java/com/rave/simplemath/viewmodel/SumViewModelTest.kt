package com.rave.simplemath.viewmodel

import com.rave.simplemath.model.repo.MathRepo
import com.rave.simplemath.utilTest.CoroutinesTestExtension
import io.mockk.coEvery
import io.mockk.mockkObject
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

internal class SumViewModelTest {
    @RegisterExtension
    val coroutinesTestExtension = CoroutinesTestExtension()
    private val viewModel = SumViewModel()

    @BeforeEach
    fun SetUp(){
        mockkObject(MathRepo)
    }
@Test
@DisplayName("Testing that sum is working properly")
fun Test1() = runTest(coroutinesTestExtension.testDispatcher){
    //given
    val num1 = 5.0
    val num2 = 7
    //when
    coEvery { MathRepo.evaluateExpression("${num1}+${num2}") } coAnswers { 12.0 }
    viewModel.EvaluateExpression("${num1}+${num2}")
    val manualResult = num1 + num2
    val result = viewModel.equationState.value
        //then
    Assertions.assertEquals(manualResult, result)
}
}

