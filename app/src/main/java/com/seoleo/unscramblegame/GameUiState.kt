package com.seoleo.unscramblegame

import android.view.View
import com.seoleo.unscramblegame.databinding.ActivityMainBinding
import java.io.Serializable

interface GameUiState : Serializable {

    fun update(binding: ActivityMainBinding)

    abstract class Abstract(
        private val scrambledWord: String,
        private val inputUiState: InputUiState,
        private val checkUiState: CheckUiState,
        private val nextButtonVisibility: Int = View.GONE,
        private val skipButtonVisibility: Int = View.VISIBLE,
    ) : GameUiState {

        override fun update(binding: ActivityMainBinding) {
            with(binding) {
                scrambledWordTextView.text = scrambledWord
                inputUiState.update(inputLayout, inputEditText)
                checkUiState.update(checkButton)
                nextButton.visibility = nextButtonVisibility
                skipButton.visibility = skipButtonVisibility
            }
        }
    }

    data class Initial(
        private val scrambledWord: String,
    ) : Abstract(
        scrambledWord = scrambledWord,
        inputUiState = InputUiState.Initial,
        checkUiState = CheckUiState.Disabled
    )

    data class Correct(val scrambledWord: String) : Abstract(
        scrambledWord = scrambledWord,
        inputUiState = InputUiState.Correct,
        checkUiState = CheckUiState.Invisible,
        skipButtonVisibility = View.GONE,
        nextButtonVisibility = View.VISIBLE
    )

    data class Incorrect(val scrambledWord: String) : Abstract(
        scrambledWord = scrambledWord,
        inputUiState = InputUiState.Incorrect,
        checkUiState = CheckUiState.Disabled
    )

    data class Sufficient(val scrambledWord: String) : Abstract(
        scrambledWord = scrambledWord,
        inputUiState = InputUiState.Sufficient,
        checkUiState = CheckUiState.Enabled
    )

    data class Insufficient(val scrambledWord: String) : Abstract(
        scrambledWord = scrambledWord,
        inputUiState = InputUiState.Insufficient,
        checkUiState = CheckUiState.Disabled
    )
}

