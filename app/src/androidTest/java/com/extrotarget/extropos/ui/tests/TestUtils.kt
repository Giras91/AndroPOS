package com.extrotarget.extropos.ui.tests

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.NoMatchingViewException

/**
 * Polls for a view with the given id to appear within the timeout (ms). Throws if not found.
 */
fun waitForView(id: Int, timeoutMs: Long) {
    val start = System.currentTimeMillis()
    while (System.currentTimeMillis() - start < timeoutMs) {
        try {
            onView(withId(id))
            return
        } catch (e: NoMatchingViewException) {
            Thread.sleep(100)
        }
    }
    // Final attempt to show the original exception
    onView(withId(id))
}
