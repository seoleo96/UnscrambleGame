package com.seoleo.unscramblegame

import android.view.View
import android.widget.Button
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import org.hamcrest.Matcher
import org.hamcrest.Matchers

class ButtonUi(
    text: String,
    id: Int,
    containerIdMatcher: Matcher<View>,
    containerClassTypeMatcher: Matcher<View>
) : AbstractButtonUi(
    interaction = onView(
        Matchers.allOf(
            ViewMatchers.withText(text),
            ViewMatchers.withId(id),
            containerIdMatcher,
            containerClassTypeMatcher,
            ViewMatchers.isAssignableFrom(Button::class.java)
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
        interaction.check(doesNotExist())
    }

    fun assertVisible() {
        interaction.check(matches(isDisplayed()))
    }
}
