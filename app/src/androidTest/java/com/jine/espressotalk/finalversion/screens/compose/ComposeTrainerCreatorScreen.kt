package com.jine.espressotalk.finalversion.screens.compose

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import com.jine.espressotalk.tests.TestTags

class ComposeTrainerCreatorScreen(private val composeRule: AndroidComposeTestRule<*, *>) {

    private val trainerNameField = hasTestTag(TestTags.trainerNameField)
    private val starterPokemonNameField = hasTestTag(TestTags.starterPokemonNameField)
    private val createTrainerButton = hasTestTag(TestTags.createTrainerButton)

    fun enterTrainerName(trainerName: String) {
        composeRule.onNode(trainerNameField).performTextInput(trainerName)
    }

    fun clickOnStarterPokemonField() {
        composeRule.onNode(starterPokemonNameField).performClick()
    }

    fun verifyStarterPokemonNameIs(pokemonName: String) {
        composeRule.onNode(starterPokemonNameField).assertTextEquals(pokemonName)
    }

    fun verifyCreateButtonIsDisabled() {
        composeRule.onNode(createTrainerButton).assertIsNotEnabled()
    }

    fun verifyCreateButtonIsEnabled() {
        composeRule.onNode(createTrainerButton).assertIsEnabled()
    }

}