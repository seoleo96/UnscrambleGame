package com.seoleo.unscramblegame

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test


class GameViewModelTest {

    private lateinit var viewModel: GameViewModel

    @Before
    fun setUp() {
        viewModel = GameViewModel(repository = FakeGameRepository())
    }

    /**
     * UG-TC-01 Skip test case
     */
    @Test
    fun skipTest() {
        val actual: GameUiState = viewModel.skip()
        val expected: GameUiState = GameUiState.Initial(
            scrambledWord = "android"
        )
        assertEquals(expected, actual)
    }

    /**
     * UG-TC-02 Insufficient Input Test Case
     */
    @Test
    fun insufficientInputTest() {
        val actual: GameUiState = viewModel.handleUserInput(text = "androi")
        val expected: GameUiState = GameUiState.Insufficient(
            scrambledWord = "android"
        )
        assertEquals(expected, actual)

        val actual: GameUiState = viewModel.handleUserInput(text = "androidd")
        val expected: GameUiState = GameUiState.Insufficient(
            scrambledWord = "android"
        )
        assertEquals(expected, actual)
    }

    /**
     * UG-TC-03 Sufficient Input Test Case
     */
    @Test
    fun sufficientInputTest() {
        val actual: GameUiState = viewModel.handleUserInput(text = "androit")
        val expected: GameUiState = GameUiState.Sufficient(
            scrambledWord = "android"
        )
        assertEquals(expected, actual)
    }

    /**
     * UG-TC-04 Incorrect Test Case
     */
    @Test
    fun incorrectTest() {
        val actual: GameUiState = viewModel.check(text = "androit")
        val expected: GameUiState = GameUiState.Incorrect(
            scrambledWord = "android"
        )
        assertEquals(expected, actual)
    }

    /**
     * UG-TC-05 Skip After Incorrect Test Case
     */
    @Test
    fun skipAfterIncorrectTest() {
        val actual: GameUiState = viewModel.check(text = "androit")
        val expected: GameUiState = GameUiState.Incorrect(
            scrambledWord = "android"
        )
        assertEquals(expected, actual)

        val actual: GameUiState = viewModel.skip()
        val expected: GameUiState = GameUiState.Incorrect(
            scrambledWord = "develop"
        )
        assertEquals(expected, actual)
    }

    /**
     * UG-TC-06 Correct After Incorrect Test Case
     */
    @Test
    fun correctAfterIncorrectTest() {
        val actual: GameUiState = viewModel.check(text = "androit")
        val expected: GameUiState = GameUiState.Incorrect(
            scrambledWord = "android"
        )
        assertEquals(expected, actual)

        val actual: GameUiState = viewModel.handleUserInput(text = "android")
        val expected: GameUiState = GameUiState.Correct(
            scrambledWord = "android"
        )
        assertEquals(expected, actual)
    }
}


interface GameRepository

class FakeGameRepository : GameRepository {

}