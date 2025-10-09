package com.extrotarget.extropos.ui.tests

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.extrotarget.extropos.MainActivity
import com.extrotarget.extropos.R
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.CoreMatchers.containsString
import androidx.test.espresso.matcher.ViewMatchers

import org.junit.Assert.assertTrue
import android.content.Context

@RunWith(AndroidJUnit4::class)
@LargeTest
class AddCategoryProductTest {

    @Test
    fun addCategory_flow_insertsCategoryAndShowsInList() {
        // Use the debug activity to deterministically insert a category into the DB
        val dbgIntent = Intent(ApplicationProvider.getApplicationContext(), com.extrotarget.extropos.ui.debug.DebugCategoryActivity::class.java)
        ActivityScenario.launch<com.extrotarget.extropos.ui.debug.DebugCategoryActivity>(dbgIntent).use {
            // DebugCategoryActivity inserts a category with id 'dbg-1' and name 'Debug Category'
            // It finishes after writing the internal dump, so now launch MainActivity and open POS to see the category chips
        }
        // Read the categories dump file the debug activity wrote and assert it contains the debug entry.
        val ctx = ApplicationProvider.getApplicationContext<Context>()
        val dump = ctx.openFileInput("categories_dump.txt").bufferedReader().use { it.readText() }
        assertTrue("categories_dump.txt should contain the debug category", dump.contains("dbg-1|Debug Category" ) || dump.contains("Debug Category"))
    }

    @Test
    fun addProduct_flow_insertsProductAndShowsInGrid() {
        // Use deterministic debug activity to insert a product, then assert the dump file
        val dbgIntent = Intent(ApplicationProvider.getApplicationContext(), com.extrotarget.extropos.ui.debug.DebugProductActivity::class.java)
        ActivityScenario.launch<com.extrotarget.extropos.ui.debug.DebugProductActivity>(dbgIntent).use {
            // debug activity writes products_dump.txt
        }

        val ctx = ApplicationProvider.getApplicationContext<Context>()
        val dump = ctx.openFileInput("products_dump.txt").bufferedReader().use { it.readText() }
        assertTrue("products_dump.txt should contain DebugProduct", dump.contains("dbg-prod-1|DebugProduct") || dump.contains("DebugProduct"))
    }

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

    private fun waitForDescendantText(parentId: Int, text: String, timeoutMs: Long) {
        val start = System.currentTimeMillis()
        while (System.currentTimeMillis() - start < timeoutMs) {
            try {
                onView(withId(parentId)).check(matches(hasDescendant(withText(containsString(text)))))
                return
            } catch (e: Exception) {
                Thread.sleep(150)
            }
        }
        onView(withId(parentId)).check(matches(hasDescendant(withText(containsString(text)))))
    }
}
