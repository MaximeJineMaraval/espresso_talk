package com.jine.espressotalk.finalversion.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.jine.espressotalk.finalversion.internal.Machoc
import com.jine.espressotalk.finalversion.internal.Pikachu
import com.jine.espressotalk.finalversion.screens.HomePageScreen
import com.jine.espressotalk.finalversion.screens.xml.XMLPokemonListScreen
import com.jine.espressotalk.finalversion.screens.xml.XMLTrainerCreatorScreen
import com.jine.espressotalk.ui.MainActivity
import org.junit.Rule
import org.junit.Test

class XmlListTest : EspressoTest() {

    private val homePageScreen = HomePageScreen()
    private val trainerCreatorScreen = XMLTrainerCreatorScreen()
    private val pokemonListScreen = XMLPokemonListScreen()

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun createTrainer() {
        homePageScreen.clickOnXmlButton()
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