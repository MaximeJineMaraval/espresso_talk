package com.jine.espressotalk.finalversion.tests

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.jine.espressotalk.finalversion.internal.Machoc
import com.jine.espressotalk.finalversion.internal.Pikachu
import com.jine.espressotalk.finalversion.screens.HomePageScreen
import com.jine.espressotalk.finalversion.screens.compose.ComposePokemonListScreen
import com.jine.espressotalk.finalversion.screens.compose.ComposeTrainerCreatorScreen
import com.jine.espressotalk.ui.MainActivity
import org.junit.Rule
import org.junit.Test

class ComposeListTest : EspressoTest() {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    private val homePageScreen = HomePageScreen()
    private val trainerCreatorScreen = ComposeTrainerCreatorScreen(rule)
    private val pokemonListScreen = ComposePokemonListScreen(rule)

    @Test
    fun createTrainer() {
        homePageScreen.clickOnComposeButton()
        Thread.sleep(DELAY)
        trainerCreatorScreen.verifyCreateButtonIsDisabled()
        Thread.sleep(DELAY)
        trainerCreatorScreen.enterTrainerName("Maxime")
        Thread.sleep(DELAY)
        trainerCreatorScreen.verifyCreateButtonIsDisabled()
        Thread.sleep(DELAY)
        trainerCreatorScreen.clickOnStarterPokemonField()
        Thread.sleep(DELAY)
        pokemonListScreen.scrollTo(Pikachu)
        Thread.sleep(DELAY)
        pokemonListScreen.clickTo(Pikachu)
        Thread.sleep(DELAY)
        trainerCreatorScreen.clickOnStarterPokemonField()
        Thread.sleep(DELAY)
        pokemonListScreen.scrollTo(Machoc)
        Thread.sleep(DELAY)
        pokemonListScreen.clickTo(Machoc)
        Thread.sleep(DELAY)
        trainerCreatorScreen.verifyStarterPokemonNameIs(Machoc.name)
        Thread.sleep(DELAY)
        trainerCreatorScreen.verifyCreateButtonIsEnabled()
        Thread.sleep(DELAY)
    }

    companion object {
        private const val DELAY = 1000L
    }

}