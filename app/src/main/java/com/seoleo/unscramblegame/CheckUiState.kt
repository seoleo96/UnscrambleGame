package com.seoleo.unscramblegame

import android.view.View
import android.widget.Button
import java.io.Serializable

interface CheckUiState : Serializable {
    fun update(checkButton: Button)

    abstract class Abstract(
        private val visible: Int,
        private val enabled: Boolean,
    ) : CheckUiState {

        override fun update(checkButton: Button) {
            with(checkButton) {
                visibility = visible
                isEnabled = enabled
            }
        }
    }

    object Disabled : Abstract(View.VISIBLE, false) {
        private fun readResolve(): Any = Disabled
    }

    object Enabled : Abstract(View.VISIBLE, true) {
        private fun readResolve(): Any = Enabled
    }

    object Invisible : Abstract(View.GONE, false) {
        private fun readResolve(): Any = Invisible
    }
}