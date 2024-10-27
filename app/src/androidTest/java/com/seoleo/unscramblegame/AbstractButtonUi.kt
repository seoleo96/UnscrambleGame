package com.seoleo.unscramblegame

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions

abstract class AbstractButtonUi(
    protected val interaction: ViewInteraction
) {

    fun click(){
        interaction.perform(ViewActions.click())
    }
}