package com.jine.espressotalk.finalversion.screens

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.jine.espressotalk.R

class HomePageScreen {

    private val xmlButton = withId(R.id.xmlButton)
    private val composeButton = withId(R.id.composeButton)

    fun clickOnXmlButton() {
        onView(xmlButton).perform(click())
    }

    fun clickOnComposeButton() {
        onView(composeButton).perform(click())
    }

}