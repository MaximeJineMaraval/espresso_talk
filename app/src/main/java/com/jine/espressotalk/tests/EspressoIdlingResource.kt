package com.jine.espressotalk.tests

import androidx.test.espresso.idling.CountingIdlingResource
import com.jine.espressotalk.BuildConfig

object EspressoIdlingResource {

    private const val RESOURCE = "GLOBAL"

    @JvmField
    var countingIdlingResource = CountingIdlingResource(RESOURCE)

    @JvmStatic
    fun increment() {
        if (BuildConfig.DEBUG) {
            countingIdlingResource.increment()
        }
    }

    @JvmStatic
    fun decrement() {
        if (BuildConfig.DEBUG) {
            if (!countingIdlingResource.isIdleNow) {
                countingIdlingResource.decrement()
            }
        }
    }
}