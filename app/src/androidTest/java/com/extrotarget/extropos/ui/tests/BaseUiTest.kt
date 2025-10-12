package com.extrotarget.extropos.ui.tests

import androidx.test.espresso.IdlingPolicies
import java.util.concurrent.TimeUnit

/**
 * Base class for UI tests to stabilize Espresso timeouts on slower devices.
 */
open class BaseUiTest {
    companion object {
        init {
            // Increase timeouts to reduce flakiness on slower devices/CI
            IdlingPolicies.setMasterPolicyTimeout(15, TimeUnit.SECONDS)
            IdlingPolicies.setIdlingResourceTimeout(15, TimeUnit.SECONDS)
        }
    }
}
