package com.extrotarget.extropos.ui.tests

// Use our Hilt host activity helper instead of FragmentScenario to ensure Hilt + theme
import com.extrotarget.extropos.ui.tests.launchFragmentInHiltContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltAndroidRule
// Hilt rule not required when using custom test host activity via alias
import org.junit.Test
import org.junit.runner.RunWith
// no-op
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import com.extrotarget.extropos.ui.menu.MenuFragment
import com.extrotarget.extropos.ui.product.ProductsGridFragment
import com.extrotarget.extropos.R
import org.hamcrest.CoreMatchers.containsString
import javax.inject.Inject
import com.extrotarget.extropos.data.local.AppDatabase
import java.util.concurrent.Executors
import com.extrotarget.extropos.ui.tests.TestDataSeeder

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@LargeTest
class UiDialogFlowsTest : BaseUiTest() {

    @get:org.junit.Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject lateinit var db: AppDatabase
    private lateinit var seeder: TestDataSeeder

    @org.junit.Before
    fun setup() {
        hiltRule.inject()
        // Ensure a clean slate before each test to avoid flakiness from prior runs
        Executors.newSingleThreadExecutor().submit { db.clearAllTables() }.get()
        seeder = TestDataSeeder(
            db.categoryDao(),
            db.productDao(),
            db.menuItemDao(),
            db.orderDao()
        )
    }
    @Test
    fun seedCategoryAndProduct_thenRecyclerShowsItems() {
        // Seed a category and a product directly
        seeder.seedCategory("test-cat-2", "Seeded Category", "Seeded for test", 1)
        seeder.seedProduct("test-prod-2", "Seeded Product", 999, stockQuantity = 5, categoryId = "test-cat-2")

        // Launch MenuFragment and verify category
        launchFragmentInHiltContainer<MenuFragment>(themeResId = androidx.appcompat.R.style.Theme_AppCompat)
        onView(withId(R.id.categoriesRecyclerView)).check(matches(hasDescendant(withText(containsString("Seeded Category")))))

        // Launch ProductsGridFragment and verify product
        launchFragmentInHiltContainer<ProductsGridFragment>(themeResId = androidx.appcompat.R.style.Theme_AppCompat)
        onView(withId(R.id.productsRecyclerView)).check(matches(hasDescendant(withText(containsString("Seeded Product")))))
    }

    // Use a Hilt-enabled host activity to attach fragments under test

    @Test
    fun addCategory_dialogAdds_thenRecyclerShowsItem() {

        // Launch MenuFragment inside a Hilt-enabled test activity with an AppCompat theme
        launchFragmentInHiltContainer<MenuFragment>(themeResId = androidx.appcompat.R.style.Theme_AppCompat)

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

        // Launch ProductsGridFragment inside a Hilt-enabled test activity with an AppCompat theme
        launchFragmentInHiltContainer<ProductsGridFragment>(themeResId = androidx.appcompat.R.style.Theme_AppCompat)

        onView(withId(R.id.addProductFab)).perform(click())

        onView(withId(R.id.dialog_product_id_input)).perform(typeText("uitest-prod-1"), closeSoftKeyboard())
        onView(withId(R.id.dialog_product_name_input)).perform(typeText("UITest Product"), closeSoftKeyboard())
        onView(withId(R.id.dialog_product_price_input)).perform(typeText("5.00"), closeSoftKeyboard())
        onView(withId(R.id.dialog_product_category_input)).perform(typeText("1"), closeSoftKeyboard())

        onView(withText("Add")).perform(click())

        onView(withId(R.id.productsRecyclerView)).check(matches(hasDescendant(withText(containsString("UITest Product")))))

    }
}
