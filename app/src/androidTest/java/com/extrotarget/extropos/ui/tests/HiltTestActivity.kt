package com.extrotarget.extropos.ui.tests

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HiltTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Apply theme if provided
        intent.getIntExtra("theme", 0).let { themeResId ->
            if (themeResId != 0) {
                setTheme(themeResId)
            }
        }
        super.onCreate(savedInstanceState)
    }
}
