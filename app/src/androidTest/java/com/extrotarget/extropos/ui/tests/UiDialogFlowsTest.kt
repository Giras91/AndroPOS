package com.extrotarget.extropos.ui.tests

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import com.extrotarget.extropos.ui.menu.MenuFragment
import com.extrotarget.extropos.ui.product.ProductsGridFragment
import com.extrotarget.extropos.R
import org.hamcrest.CoreMatchers.containsString

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@LargeTest
class UiDialogFlowsTest {

    @Test
    fun addCategory_dialogAdds_thenRecyclerShowsItem() {

    // Launch MenuFragment using standard FragmentScenario without theme - works with HiltTestApplication
    launchFragmentInContainer<MenuFragment>()

        // Click the add category FAB
        onView(withId(R.id.addCategoryFab)).perform(click())

        // Fill form inputs which have stable ids
        onView(withId(R.id.dialog_category_id_input)).perform(typeText("uitest-cat-1"), closeSoftKeyboard())
        onView(withId(R.id.dialog_category_name_input)).perform(typeText("UITest Category"), closeSoftKeyboard())
        onView(withText("Add")).perform(click())

    // Verify the categories RecyclerView contains the new category
    onView(withId(R.id.categoriesRecyclerView)).check(matches(hasDescendant(withText(containsString("UITest Category")))))
    }

    @Test
    fun addProduct_dialogAdds_thenRecyclerShowsItem() {

    launchFragmentInContainer<ProductsGridFragment>()

        onView(withId(R.id.addProductFab)).perform(click())

        onView(withId(R.id.dialog_product_id_input)).perform(typeText("uitest-prod-1"), closeSoftKeyboard())
        onView(withId(R.id.dialog_product_name_input)).perform(typeText("UITest Product"), closeSoftKeyboard())
        onView(withId(R.id.dialog_product_price_input)).perform(typeText("5.00"), closeSoftKeyboard())
        onView(withId(R.id.dialog_product_category_input)).perform(typeText("1"), closeSoftKeyboard())

        onView(withText("Add")).perform(click())

        onView(withId(R.id.productsRecyclerView)).check(matches(hasDescendant(withText(containsString("UITest Product")))))

    }
}
