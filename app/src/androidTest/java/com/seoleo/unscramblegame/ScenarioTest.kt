package com.seoleo.unscramblegame

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule<MainActivity>(MainActivity::class.java)

    private lateinit var gamePage: GamePage

    fun setUp() {
        gamePage = GamePage(scrumbledWord = "android")
    }

    /**
     * UGTC-01 Skip test case
     */
    @Test
    fun skipTest() {
        gamePage.clickSkip()
        gamePage = GamePage(scrumbledWord = "chrome")
        gamePage.assertInitialState()
    }

    /**
     * UGTC-02 Insufficient input test case
     */
    @Test
    fun insufficientInputTest() {
        gamePage.input("adnoi")
        gamePage.asserInsufficientInputState()

        gamePage.input("androidd")
        gamePage.asserInsufficientInputState()
    }

    /**
     * UGTC-03 Sufficient input test case
     */
    @Test
    fun sufficientInputTest() {
        gamePage.input("androit")
        gamePage.assertSufficientInputState()
    }

    /**
     * UGTC-05 Incorrect test case
     */
    @Test
    fun incorrectTest() {
        gamePage.input("androit")
        gamePage.clickCheck()
        gamePage.assertIncorrectState()
    }

    /**
     * UGTC-06 Skip after incorrect test case
     */
    @Test
    fun skipAfterIncorrectTest() {
        gamePage.input("androidt")

        gamePage.clickCheck()
        gamePage.assertIncorrectState()

        gamePage.clickSkip()
        gamePage = GamePage(scrumbledWord = "chrome")
        gamePage.assertInitializedState()
    }

    /**
     * UGTC-07 Correct after incorrect test case
     */
    @Test
    fun correctAfterIncorrectTest() {
        gamePage.input("androit")

        gamePage.clickCheck()
        gamePage.assertIncorrectState()

        gamePage.input("androi")
        gamePage.assertInsufficientInputState()

        gamePage.input("android")
        gamePage.clickCheck()
        gamePage.assertCorrectState()

        gamePage.clickNext()
        gamePage = GamePage(scrumbledWord = "chrome")
        gamePage.initialState()
    }
}