package com.extrotarget.extropos.ui.tests
import android.os.Bundle
import androidx.annotation.StyleRes
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.test.core.app.ActivityScenario
// no-op

/**
 * Launches a fragment inside a HiltTestActivity so the fragment can use Hilt injection.
 * Uses a simple approach - directly launch the activity class without complex intent resolution.
 */
inline fun <reified T : Fragment> launchFragmentInHiltContainer(
    fragmentArgs: Bundle? = null,
    @StyleRes themeResId: Int = androidx.appcompat.R.style.Theme_AppCompat,
    fragmentFactory: FragmentFactory? = null,
    crossinline action: T.() -> Unit = {}
): ActivityScenario<HiltTestActivity> {
    // Launch the Hilt-provided test activity with the requested theme
    val intent = Intent(Intent.ACTION_MAIN).setClassName(
        androidx.test.core.app.ApplicationProvider.getApplicationContext(),
        HiltTestActivity::class.java.name
    ).putExtra("theme", themeResId)

    val scenario = ActivityScenario.launch<HiltTestActivity>(intent)
    scenario.onActivity { activity ->
        fragmentFactory?.let {
            activity.supportFragmentManager.fragmentFactory = it
        }
        val fragment = activity.supportFragmentManager.fragmentFactory.instantiate(
            T::class.java.classLoader!!,
            T::class.java.name
        ) as T
        fragment.arguments = fragmentArgs
        activity.supportFragmentManager.beginTransaction()
            .add(android.R.id.content, fragment, "")
            .commitNow()

        fragment.action()
    }
    return scenario
}
