package com.jine.espressotalk.tests

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.jine.espressotalk.internal.Pikachu
import com.jine.espressotalk.internal.Tortank
import com.jine.espressotalk.screens.ComposeListScreen
import com.jine.espressotalk.screens.HomePageScreen
import com.jine.espressotalk.ui.MainActivity
import org.junit.Rule
import org.junit.Test

class ComposeListTest : EspressoTest() {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    private val homePageScreen = HomePageScreen()
    private val composeListScreen = ComposeListScreen(rule)

    @Test
    fun scrollToPikachu() {
        homePageScreen.clickOnComposeButton()
        Thread.sleep(1000)
        composeListScreen.scrollTo(Pikachu)
        Thread.sleep(1000)
    }

    @Test
    fun searchPikachuShowPikachu() {
        homePageScreen.clickOnComposeButton()
        composeListScreen.enterSearchText("Pikachu")
        Thread.sleep(1000)
        composeListScreen.checkIsDisplayed(Pikachu)
        Thread.sleep(1000)
    }

    @Test
    fun searchTortankHidePikachu() {
        homePageScreen.clickOnComposeButton()
        composeListScreen.enterSearchText("Tortank")
        Thread.sleep(1000)
        composeListScreen.checkIsNotDisplayed(Pikachu)
        Thread.sleep(1000)
    }

    @Test
    fun markPikachuAsFavoriteShowPikachu() {
        homePageScreen.clickOnComposeButton()
        composeListScreen.scrollTo(Pikachu)
        Thread.sleep(1000)
        composeListScreen.markAsFavorite(Pikachu)
        Thread.sleep(1000)
        composeListScreen.showOnlyFavorite()
        Thread.sleep(1000)
        composeListScreen.checkIsDisplayed(Pikachu)
        Thread.sleep(1000)
    }

    @Test
    fun markTortankAsFavoriteHidePikachu() {
        homePageScreen.clickOnComposeButton()
        composeListScreen.scrollTo(Tortank)
        Thread.sleep(1000)
        composeListScreen.markAsFavorite(Tortank)
        Thread.sleep(1000)
        composeListScreen.showOnlyFavorite()
        Thread.sleep(1000)
        composeListScreen.checkIsNotDisplayed(Pikachu)
        Thread.sleep(1000)
    }

}