package com.seoleo.unscramblegame

import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

interface InputUiState {

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
    )

    object Sufficient : Abstract(
        errorIsVisible = false,
        enabled = true,
        clearText = false
    )

    object Insufficient : Abstract(
        errorIsVisible = false,
        enabled = true,
        clearText = false
    )

    object Correct : Abstract(
        errorIsVisible = false,
        enabled = false,
        clearText = false
    )

    object Incorrect : Abstract(
        errorIsVisible = true,
        enabled = true,
        clearText = false
    )
}
