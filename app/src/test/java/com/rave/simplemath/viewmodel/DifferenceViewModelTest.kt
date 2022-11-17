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

internal class DifferenceViewModelTest {
    @RegisterExtension
    val extension = CoroutinesTestExtension()
    val differenceVM: DifferenceViewModel = DifferenceViewModel()

    @BeforeEach
    fun beforeEach() {
        mockkObject(MathRepo)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    @DisplayName("Tests difference view model's state")
    fun testDifferenceViewModel() = runTest(extension.testDispatcher) {
        // given
        val expected = 4.0
        coEvery { MathRepo.evaluateExpression("6-2", Dispatchers.IO) } coAnswers { expected }
        // when
        differenceVM.getDifference("6", "2")
        // then
        val result = differenceVM.difference.value
        assertFalse(result.isLoading)
        assertEquals(expected.toInt(), result.difference)
    }
}
