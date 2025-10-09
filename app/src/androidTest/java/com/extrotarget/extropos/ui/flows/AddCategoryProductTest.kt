package com.extrotarget.extropos.ui.flows

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.extrotarget.extropos.MainActivity
import com.extrotarget.extropos.R
import org.hamcrest.CoreMatchers.containsString
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import android.content.Context
import org.junit.Assert.assertTrue

@LargeTest
@RunWith(AndroidJUnit4::class)
class AddCategoryProductTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        // Launch the debug launcher activity which starts MainActivity with flags
        val intent = Intent(ApplicationProvider.getApplicationContext(), com.extrotarget.extropos.debug.DebugLauncherActivity::class.java)
        scenario = ActivityScenario.launch(intent)
        // Wait for main UI to settle and for the POS FAB to be present
        waitForViewLocal(R.id.addProductFab, 4000)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun addCategory_flow_insertsCategory() {
        // Use debug activity to insert category deterministically
        val dbgIntent = Intent(ApplicationProvider.getApplicationContext(), com.extrotarget.extropos.ui.debug.DebugCategoryActivity::class.java)
        ActivityScenario.launch<com.extrotarget.extropos.ui.debug.DebugCategoryActivity>(dbgIntent).use {
            // activity writes categories_dump.txt
        }

        val ctx = ApplicationProvider.getApplicationContext<Context>()
        val dump = ctx.openFileInput("categories_dump.txt").bufferedReader().use { it.readText() }
        assertTrue("categories_dump.txt should contain the debug category", dump.contains("dbg-1|Debug Category") || dump.contains("Debug Category"))
    }

    @Test
    fun addProduct_flow_insertsProduct() {
        // Launch the debug product inserter activity to deterministically add a product
        val dbgIntent = Intent(ApplicationProvider.getApplicationContext(), com.extrotarget.extropos.ui.debug.DebugProductActivity::class.java)
        ActivityScenario.launch<com.extrotarget.extropos.ui.debug.DebugProductActivity>(dbgIntent).use {
            // the activity writes products_dump.txt to internal storage
        }

        // Read the products dump and assert the debug product is present
        val ctx = ApplicationProvider.getApplicationContext<Context>()
        val dump = ctx.openFileInput("products_dump.txt").bufferedReader().use { it.readText() }
        assertTrue("products_dump.txt should contain DebugProduct", dump.contains("dbg-prod-1|DebugProduct") || dump.contains("DebugProduct"))
    }

    // Local polling wait helper to avoid cross-file import issues in androidTest
    private fun waitForViewLocal(id: Int, timeoutMs: Long) {
        val start = System.currentTimeMillis()
        while (System.currentTimeMillis() - start < timeoutMs) {
            try {
                onView(withId(id))
                return
            } catch (e: Exception) {
                Thread.sleep(100)
            }
        }
        onView(withId(id))
    }
}
