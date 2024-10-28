package com.seoleo.unscramblegame

import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.io.Serializable

interface InputUiState : Serializable {

    fun update(inputLayout: TextInputLayout, inputEditText: TextInputEditText)

    abstract class Abstract(
        private val errorIsVisible : Boolean,
        private val enabled : Boolean,
        private val clearText : Boolean,
    ) : InputUiState {

        override fun update(inputLayout: TextInputLayout, inputEditText: TextInputEditText) {
            inputLayout.isErrorEnabled = errorIsVisible
            inputLayout.isEnabled = enabled
            if(errorIsVisible){
                inputLayout.error = inputLayout.context.getString(R.string.incorrect_message)
            }
            if(clearText){
                inputEditText.setText("")
            }
        }
    }

    object Initial : Abstract(
        errorIsVisible = false,
        enabled = true,
        clearText = true
    ) {
        private fun readResolve(): Any = Initial
    }

    object Sufficient : Abstract(
        errorIsVisible = false,
        enabled = true,
        clearText = false
    ) {
        private fun readResolve(): Any = Sufficient
    }

    object Insufficient : Abstract(
        errorIsVisible = false,
        enabled = true,
        clearText = false
    ) {
        private fun readResolve(): Any = Insufficient
    }

    object Correct : Abstract(
        errorIsVisible = false,
        enabled = false,
        clearText = false
    ) {
        private fun readResolve(): Any = Correct
    }

    object Incorrect : Abstract(
        errorIsVisible = true,
        enabled = true,
        clearText = false
    ) {
        private fun readResolve(): Any = Incorrect
    }
}
