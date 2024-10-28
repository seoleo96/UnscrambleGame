package com.seoleo.unscramblegame

import android.view.KeyEvent
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.pressKey
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class InputUi(containerIdMatcher: Matcher<View>, containerClassTypeMatcher: Matcher<View>) {


    private val textInputLayoutErrorEnabledMatcherFalse = TextInputLayoutErrorEnabledMatcher(false)
    private val layoutInteraction = onView(
        allOf(
            withId(R.id.inputLayout),
            containerIdMatcher,
            containerClassTypeMatcher,
            isAssignableFrom(TextInputLayout::class.java)
        )
    )

    private val inputInteraction = onView(
        allOf(
            withId(R.id.inputEditText),
            isAssignableFrom(TextInputEditText::class.java)
        )
    )


    fun assertInitialState() {
        layoutInteraction.check(
            matches(
                allOf(
                    isEnabled(),
                    textInputLayoutErrorEnabledMatcherFalse
                )
            )
        )
        inputInteraction.check(matches(allOf(withText(""))))
    }

    fun addInput(text: String) {
        inputInteraction.perform(typeText(text), ViewActions.closeSoftKeyboard())
    }

    fun asserInsufficientInputState() {
        layoutInteraction.check(
            matches(
                allOf(
                    isEnabled(),
                    textInputLayoutErrorEnabledMatcherFalse
                )
            )
        )
    }

    fun assertSufficientInputState() {
        layoutInteraction.check(
            matches(
                allOf(
                    isEnabled(),
                    textInputLayoutErrorEnabledMatcherFalse
                )
            )
        )
    }

    fun assertIncorrectState() {
        layoutInteraction.check(
                matches(
                    allOf(
                        isEnabled(),
                        TextInputLayoutErrorEnabledMatcher(true)
                    )
                )
            )
    }

    fun assertCorrectState() {
        layoutInteraction.check(
            matches(
                allOf(
                    isNotEnabled(),
                    textInputLayoutErrorEnabledMatcherFalse
                )
            )
        )
    }

    fun removeInputLastLetter() {
        inputInteraction.perform(
            click(),
            pressKey(KeyEvent.KEYCODE_DEL),
            closeSoftKeyboard()
        )
    }

}
