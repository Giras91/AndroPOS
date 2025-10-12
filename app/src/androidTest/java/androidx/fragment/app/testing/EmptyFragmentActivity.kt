package androidx.fragment.app.testing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Test-only EmptyFragmentActivity used by FragmentScenario. Annotated with
 * @AndroidEntryPoint so Hilt can inject fragments attached to this activity.
 */
@AndroidEntryPoint
class EmptyFragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Apply theme if provided by tests
        intent.getIntExtra("theme", 0).let { themeResId ->
            if (themeResId != 0) setTheme(themeResId)
        }
        super.onCreate(savedInstanceState)
    }
}
