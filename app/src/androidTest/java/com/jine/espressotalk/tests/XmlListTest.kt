package com.jine.espressotalk.tests

import androidx.test.espresso.PerformException
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.jine.espressotalk.internal.Pikachu
import com.jine.espressotalk.internal.Tortank
import com.jine.espressotalk.screens.HomePageScreen
import com.jine.espressotalk.screens.XMLListScreen
import com.jine.espressotalk.ui.MainActivity
import org.junit.Rule
import org.junit.Test

class XmlListTest : EspressoTest() {

    private val homePageScreen = HomePageScreen()
    private val xmlListScreen = XMLListScreen()

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun scrollToPikachu() {
        homePageScreen.clickOnXmlButton()
        Thread.sleep(1000)
        xmlListScreen.scrollTo(Pikachu)
        Thread.sleep(1000)
    }

    @Test
    fun searchPikachuShowPikachu() {
        homePageScreen.clickOnXmlButton()
        xmlListScreen.enterSearchText("Pikachu")
        Thread.sleep(1000)
        xmlListScreen.scrollTo(Pikachu)
        Thread.sleep(1000)
    }

    @Test(expected = PerformException::class)
    fun searchTortankHidePikachu() {
        homePageScreen.clickOnXmlButton()
        xmlListScreen.enterSearchText("Tortank")
        Thread.sleep(1000)
        xmlListScreen.scrollTo(Pikachu)
        Thread.sleep(1000)
    }

    @Test
    fun markPikachuAsFavoriteShowPikachu() {
        homePageScreen.clickOnXmlButton()
        xmlListScreen.scrollTo(Pikachu)
        Thread.sleep(1000)
        xmlListScreen.markAsFavorite(Pikachu)
        Thread.sleep(1000)
        xmlListScreen.showOnlyFavorite()
        Thread.sleep(1000)
        xmlListScreen.scrollTo(Pikachu)
        Thread.sleep(1000)
    }

    @Test(expected = PerformException::class)
    fun markTortankAsFavoriteHidePikachu() {
        homePageScreen.clickOnXmlButton()
        xmlListScreen.scrollTo(Pikachu)
        Thread.sleep(1000)
        xmlListScreen.markAsFavorite(Tortank)
        Thread.sleep(1000)
        xmlListScreen.showOnlyFavorite()
        Thread.sleep(1000)
        xmlListScreen.scrollTo(Pikachu)
        Thread.sleep(1000)
    }

}