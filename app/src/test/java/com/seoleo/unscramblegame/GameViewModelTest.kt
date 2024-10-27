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
        var actual: GameUiState = viewModel.handleUserInput(text = "androi")
        var expected: GameUiState = GameUiState.Insufficient(
            scrambledWord = "android"
        )
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "androidd")
        expected = GameUiState.Insufficient(
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
        var actual: GameUiState = viewModel.check(text = "androit")
        var expected: GameUiState = GameUiState.Incorrect(
            scrambledWord = "android"
        )
        assertEquals(expected, actual)

        actual = viewModel.skip()
        expected = GameUiState.Incorrect(
            scrambledWord = "develop"
        )
        assertEquals(expected, actual)
    }

    /**
     * UG-TC-06 Correct After Incorrect Test Case
     */
    @Test
    fun correctAfterIncorrectTest() {
        var actual: GameUiState = viewModel.check(text = "androit")
        var expected: GameUiState = GameUiState.Incorrect(
            scrambledWord = "android"
        )
        assertEquals(expected, actual)

        actual = viewModel.handleUserInput(text = "android")
        expected = GameUiState.Correct(
            scrambledWord = "android"
        )
        assertEquals(expected, actual)
    }
}


private interface GameRepository {
    fun scrambledWord(): String
    fun originalWord(): String
    fun next()
    fun check(text: String): Boolean
}

private class FakeGameRepository : GameRepository {
    private var index = 0
    private val originalList = listOf("android", "develop")
    private val scrambledList = originalList.map { it.reversed() }

    override fun scrambledWord(): String = scrambledList[index]

    override fun originalWord(): String = originalList[index]

    override fun next() {
        if (index == originalList.size) {
            index = 0
        }
        ++index
    }

    override fun check(text: String): Boolean = text == originalList[index]
}