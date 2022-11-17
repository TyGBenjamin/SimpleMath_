package com.rave.simplemath.viewmodel

import com.rave.simplemath.model.repo.MathRepo
import com.rave.simplemath.utilTest.CoroutinesTestExtension
import io.mockk.mockkObject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

/**
 * Main view model test.
 *
 * @constructor Create empty Main view model test
 */
internal class MainViewModelTest {

    @RegisterExtension
    private val extension = CoroutinesTestExtension()
    private val vm = MainViewModel()

    @BeforeEach
    fun setUp() {
        mockkObject(MathRepo)
    }


    @Test
    fun evaluateAddExpression() {
    }

}

