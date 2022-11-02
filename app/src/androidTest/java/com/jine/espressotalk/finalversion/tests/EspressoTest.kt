package com.jine.espressotalk.finalversion.tests

import androidx.test.espresso.IdlingRegistry
import com.jine.espressotalk.tests.EspressoIdlingResource
import org.junit.After
import org.junit.Before

abstract class EspressoTest {

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }
}