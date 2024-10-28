package com.seoleo.unscramblegame

import android.view.View
import android.widget.Button
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.not

class ButtonUi(
    @StringRes text: Int,
    id: Int,
    containerIdMatcher: Matcher<View>,
    containerClassTypeMatcher: Matcher<View>
) : AbstractButtonUi(
    interaction = onView(
        Matchers.allOf(
            withText(text),
            withId(id),
            containerIdMatcher,
            containerClassTypeMatcher,
            isAssignableFrom(Button::class.java)
        )
    )
) {

    fun assertEnabled() {
        interaction.check(matches(isEnabled()))
    }

    fun assertNotEnabled() {
        interaction.check(matches(isNotEnabled()))
    }

    fun assertNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

    fun assertVisible() {
        interaction.check(matches(isDisplayed()))
    }
}
