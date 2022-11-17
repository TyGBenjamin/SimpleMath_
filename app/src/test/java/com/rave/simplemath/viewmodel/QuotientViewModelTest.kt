package com.rave.simplemath.viewmodel

import com.rave.simplemath.model.repo.MathRepo
import com.rave.simplemath.utilTest.CoroutinesTestExtension
import io.mockk.coEvery
import io.mockk.mockkObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

internal class QuotientViewModelTest {
    @RegisterExtension
    val extension = CoroutinesTestExtension()
    val quotientVM: QuotientViewModel = QuotientViewModel()

    @BeforeEach
    fun beforeEach() {
        mockkObject(MathRepo)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    @DisplayName("Tests sum view model's state")
    fun testSumViewModel() = runTest(extension.testDispatcher) {
        // given
        val expected = 4.0
        coEvery { MathRepo.evaluateExpression("8%2F2", Dispatchers.IO) } coAnswers { expected }
        // when
        quotientVM.getQuotient("8", "2")
        // then
        val result = quotientVM.quotient.value
        assertFalse(result.isLoading)
        assertEquals(expected, result.quotient)
    }
}