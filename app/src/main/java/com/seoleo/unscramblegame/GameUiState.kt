package com.seoleo.unscramblegame

import com.seoleo.unscramblegame.databinding.ActivityMainBinding

interface GameUiState {

    fun update(binding: ActivityMainBinding)

    data class Initial(val scrambledWord: String) : GameUiState {
        override fun update(binding: ActivityMainBinding) {
            TODO("Not yet implemented")
        }
    }

    data class Correct(val scrambledWord: String) : GameUiState {
        override fun update(binding: ActivityMainBinding) {
            TODO("Not yet implemented")
        }
    }

    data class Incorrect(val scrambledWord: String) : GameUiState {
        override fun update(binding: ActivityMainBinding) {
            TODO("Not yet implemented")
        }
    }

    data class Sufficient(val scrambledWord: String) : GameUiState {
        override fun update(binding: ActivityMainBinding) {
            TODO("Not yet implemented")
        }
    }

    data class Insufficient(val scrambledWord: String) : GameUiState {
        override fun update(binding: ActivityMainBinding) {
            TODO("Not yet implemented")
        }
    }
}

