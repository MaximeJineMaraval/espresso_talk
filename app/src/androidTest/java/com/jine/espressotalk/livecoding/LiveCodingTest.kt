package com.jine.espressotalk.livecoding

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.jine.espressotalk.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import com.jine.espressotalk.R
import com.jine.espressotalk.tests.EspressoIdlingResource
import com.jine.espressotalk.ui.trainercreator.xml.PokemonAdapter
import org.junit.After
import org.junit.Before

class LiveCodingTest {

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }
    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    private val createButton = withId(R.id.createButton)

    @Test
    fun myFirstTest() {
        Thread.sleep(DELAY)
        onView(withId(R.id.xmlButton)).perform(click())
        Thread.sleep(DELAY)
        onView(createButton).check(matches(isNotEnabled()))
        onView(withId(R.id.trainerInputEditText)).perform(typeText("Maxime"))
        onView(withId(R.id.starterInputEditText)).perform(click())
        onView(withId(R.id.list)).perform(scrollTo<PokemonAdapter.PokemonViewHolder>(hasDescendant(withText("Pikachu"))))
        Thread.sleep(DELAY)
        onView(withText("Pikachu")).perform(click())
        Thread.sleep(DELAY)
        onView(withId(R.id.starterInputEditText)).check(matches(withText("Pikachu")))
        Thread.sleep(DELAY)
        onView(createButton).check(matches(isEnabled()))
    }

    companion object {
        private const val DELAY = 1000L
    }

}


//    @get:Rule
//    var activityRule: ActivityScenarioRule<MainActivity> =
//        ActivityScenarioRule(MainActivity::class.java)

//    companion object {
//        private const val DELAY = 1000L
//    }

//
//    @Before
//    fun registerIdlingResource() {
//        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
//    }
//
//    @After
//    fun unregisterIdlingResource() {
//        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
//    }

//@Test
//fun myFirstTest() {
//    Thread.sleep(LiveCodingTest.DELAY)
//    onView(withId(R.id.xmlButton)).perform(click())
//    Thread.sleep(LiveCodingTest.DELAY)
//    onView(withId(R.id.createButton)).check(matches(isNotEnabled()))
//    onView(withId(R.id.trainerInputEditText)).perform(typeText("Maxime"))
//    onView(withId(R.id.createButton)).check(matches(isNotEnabled()))
//    Thread.sleep(LiveCodingTest.DELAY)
//    onView(withId(R.id.starterInputEditText)).perform(click())
//    onView(withId(R.id.list)).perform(scrollTo<PokemonAdapter.PokemonViewHolder>(hasDescendant(withText("Pikachu"))))
//    Thread.sleep(LiveCodingTest.DELAY)
//    onView(withText("Pikachu")).perform(click())
//    Thread.sleep(LiveCodingTest.DELAY)
//    onView(withId(R.id.starterInputEditText)).check(matches(withText("Pikachu")))
//    onView(withId(R.id.createButton)).check(matches(isEnabled()))
//}