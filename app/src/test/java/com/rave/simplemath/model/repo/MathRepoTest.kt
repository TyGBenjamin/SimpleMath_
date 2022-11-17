package com.rave.simplemath.model.repo

import com.rave.simplemath.model.remote.MathService
import com.rave.simplemath.model.remote.RetrofitObject
import com.rave.simplemath.utilTest.CoroutinesTestExtension
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

@OptIn(ExperimentalCoroutinesApi::class)
internal class MathRepoTest {

    @RegisterExtension
    private val extension = CoroutinesTestExtension()
    private val mockService = mockk<MathService>()

    @BeforeEach
    fun beforeEach() {
        mockkObject(RetrofitObject)
        every { RetrofitObject.getMathService() } answers { mockService }
    }

    @Test
    @DisplayName("Testing that we can evaluate expressions")
    fun testEvaluateExpression() = runTest(extension.testDispatcher) {
        // Given
        val expr = "2+2"
        val result: Double = 4.0
        coEvery { mockService.evaluateExpression(expr) } coAnswers { result }

        // When
        val testResult = MathRepo.evaluateExpression(expr)

        // Then
        Assertions.assertEquals(result.toString(), testResult)
    }
}
