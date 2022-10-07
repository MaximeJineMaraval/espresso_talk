package com.jine.espressotalk.internal

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.jine.espressotalk.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anyOf

fun typeSearchViewText(text: String): ViewAction {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return allOf(
                isDisplayed(),
                anyOf(
                    isAssignableFrom(SearchView::class.java),
                    isAssignableFrom(android.widget.SearchView::class.java)
                )
            )
        }

        override fun getDescription(): String {
            return "Change view text"
        }

        override fun perform(uiController: UiController?, view: View?) {
            when (view) {
                is SearchView -> view.setQuery(text, false)
                is android.widget.SearchView -> view.setQuery(text, false)
            }
        }

    }
}

fun clickOnFavoriteItemIcon(): ViewAction {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return allOf(isDisplayed())
        }

        override fun getDescription(): String {
            return "Click on favorite icon"
        }

        override fun perform(uiController: UiController?, view: View?) {
            view?.findViewById<View>(R.id.favorite)?.performClick()
        }

    }
}