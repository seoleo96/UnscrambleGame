package com.seoleo.unscramblegame

import androidx.lifecycle.ViewModel

class GameViewModel(
    private val repository: GameRepository,
) : ViewModel() {

    fun init(): GameUiState = GameUiState.Initial(scrambledWord = repository.scrambledWord())

    fun next(): GameUiState {
        repository.next()
        return init()
    }

    fun skip(): GameUiState {
        repository.next()
        return init()
    }

    fun check(text: String): GameUiState {
        val scrambledWord = repository.scrambledWord()
        return if (repository.originalWord().equals(text, true)) {
            GameUiState.Correct(scrambledWord = scrambledWord)
        } else {
            GameUiState.Incorrect(scrambledWord = scrambledWord)
        }
    }

    fun handleUserInput(text: String): GameUiState {
        val scrambledWord = repository.scrambledWord()
        return if (text.length == scrambledWord.length)
            GameUiState.Sufficient(scrambledWord = scrambledWord)
        else
            GameUiState.Insufficient(scrambledWord = scrambledWord)
    }
}