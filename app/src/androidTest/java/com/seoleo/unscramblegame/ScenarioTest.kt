package com.seoleo.unscramblegame

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule<MainActivity>(MainActivity::class.java)

    private lateinit var gamePage: GamePage

    @Before
    fun setUp() {
        gamePage = GamePage(scrambledWord = "diordna")
    }

    /**
     * UGTC-01 Skip test case
     */
    @Test
    fun skipTest() {
        gamePage.clickSkip()
        gamePage = GamePage(scrambledWord = "poleved")
        gamePage.assertInitialState()

        activityScenarioRule.scenario.recreate()
        gamePage.assertInitialState()

    }

    /**
     * UGTC-02 Insufficient input test case
     */
    @Test
    fun insufficientInputTest() {
        gamePage.input("adnroi")
        gamePage.assertInsufficientInputState()
    }

    /**
     * UGTC-03 Sufficient input test case
     */
    @Test
    fun sufficientInputTest() {
        gamePage.input("androit")
        gamePage.assertSufficientInputState()

        activityScenarioRule.scenario.recreate()
        gamePage.assertInsufficientInputState()
    }

    /**
     * UGTC-05 Incorrect test case
     */
    @Test
    fun incorrectTest() {
        gamePage.input("androit")
        gamePage.clickCheck()
        gamePage.assertIncorrectState()

        activityScenarioRule.scenario.recreate()
        gamePage.assertSufficientInputState()
    }

    /**
     * UGTC-06 Skip after incorrect test case
     */
    @Test
    fun skipAfterIncorrectTest() {
        gamePage.input("androidt")

        gamePage.clickCheck()
        gamePage.assertIncorrectState()

        activityScenarioRule.scenario.recreate()
        gamePage.assertSufficientInputState()

        gamePage.clickSkip()
        gamePage = GamePage(scrambledWord = "poleved")
        gamePage.assertInitialState()

        activityScenarioRule.scenario.recreate()
        gamePage.assertSufficientInputState()
    }

    /**
     * UGTC-07 Correct after incorrect test case
     */
    @Test
    fun correctAfterIncorrectTest() {
        gamePage.input("androit")

        gamePage.clickCheck()
        gamePage.assertIncorrectState()

        activityScenarioRule.scenario.recreate()
        gamePage.assertSufficientInputState()

        gamePage.removeInputLastLetter()
        gamePage.assertInsufficientInputState()

        activityScenarioRule.scenario.recreate()
        gamePage.assertSufficientInputState()

        gamePage.input("d")
        gamePage.clickCheck()
        gamePage.assertCorrectState()

        activityScenarioRule.scenario.recreate()
        gamePage.assertSufficientInputState()

        gamePage.clickNext()
        gamePage = GamePage(scrambledWord = "poleved")
        gamePage.assertInitialState()

        activityScenarioRule.scenario.recreate()
        gamePage.assertSufficientInputState()
    }
}