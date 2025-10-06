package com.extrotarget.extropos.ui.dashboard

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Temporary simple implementation for Step 1
        Toast.makeText(this, "Dashboard Activity Loaded - Step 1 Complete", Toast.LENGTH_LONG).show()
        
        // TODO: Implement full dashboard UI in Step 2
        finish()
    }
}
