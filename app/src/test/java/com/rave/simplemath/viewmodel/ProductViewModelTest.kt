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

internal class ProductViewModelTest {
    @RegisterExtension
    val extension = CoroutinesTestExtension()
    val productVM: ProductViewModel = ProductViewModel()

    @BeforeEach
    fun beforeEach() {
        mockkObject(MathRepo)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    @DisplayName("Tests product view model's state")
    fun testProductViewModel() = runTest(extension.testDispatcher) {
        // given
        val expected = "4"
        coEvery { MathRepo.evaluateExpression("2*2", Dispatchers.IO) } coAnswers { expected }
        // when
        productVM.getProduct("2", "2")
        // then
        val result = productVM.product.value
        assertFalse(result.isLoading)
        assertEquals(expected, result.product)
    }
}
