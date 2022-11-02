package com.jine.espressotalk.finalversion.screens.xml

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.jine.espressotalk.R

class XMLTrainerCreatorScreen {

    private val trainerNameField = withId(R.id.trainerInputEditText)
    private val starterPokemonNameField = withId(R.id.starterInputEditText)
    private val createTrainerButton = withId(R.id.createButton)

    fun enterTrainerName(trainerName: String) {
        onView(trainerNameField).perform(typeText(trainerName))
    }

    fun clickOnStarterPokemonField() {
        onView(starterPokemonNameField).perform(click())
    }

    fun verifyStarterPokemonNameIs(pokemonName: String) {
        onView(starterPokemonNameField).check(matches(withText(pokemonName)))
    }

    fun verifyCreateButtonIsDisabled() {
        onView(createTrainerButton).check(matches(isNotEnabled()))
    }

    fun verifyCreateButtonIsEnabled() {
        onView(createTrainerButton).check(matches(isEnabled()))
    }

}