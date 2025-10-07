package com.extrotarget.extropos.debug

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.extrotarget.extropos.R

/**
 * Debug-only launcher to quickly open the DashboardActivity from adb.
 * This file is in the debug source set and will not be included in release builds.
 */
class DebugLauncherActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            // If caller requested we simulate clicking the Cash Register tile on the
            // Dashboard, launch DashboardActivity directly and post a click.
            val simulateClick = intent?.getBooleanExtra("simulate_dashboard_cash_click", false) == true
            if (simulateClick) {
                try {
                    // Prefer launching MainActivity which hosts the newer fragment-based dashboard.
                    val mainClazz = Class.forName("com.extrotarget.extropos.MainActivity")
                    val mainIntent = Intent(this, mainClazz)
                    // Ask MainActivity to open the dashboard and POS if available
                    mainIntent.putExtra("debug_open_dashboard", true)
                    mainIntent.putExtra("debug_open_pos", true)
                    startActivity(mainIntent)

                    // Post a delayed task to find the activity's root view and perform the click.
                    // Because this runs in a separate process from the target activity's lifecycle,
                    // this is best-effort: it will attempt to send an ordered broadcast that the
                    // DashboardActivity can listen for to trigger the click. We'll also attempt
                    // a direct UI action via instrumentation-like approach (best-effort in debug).
                    Handler(Looper.getMainLooper()).postDelayed({
                        try {
                            // Broadcast an intent the dashboard fragment or activity may handle (debug-only).
                            val clickIntent = Intent("com.extrotarget.extropos.debug.SIMULATE_CASH_CLICK")
                            sendBroadcast(clickIntent)
                            Log.i("DebugLauncher", "Sent SIMULATE_CASH_CLICK broadcast")
                        } catch (e: Exception) {
                            Log.w("DebugLauncher", "Failed to send simulate click broadcast: ${e.message}")
                        }
                    }, 600)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                // Default behavior: start MainActivity and optionally open POS
                val mainClazz = Class.forName("com.extrotarget.extropos.MainActivity")
                val intent = Intent(this, mainClazz)
                intent.putExtra("debug_open_dashboard", true)
                intent.putExtra("debug_open_pos", true)
                intent.putExtra("debug_pos_search_query", "Teh")
                intent.putExtra("debug_auto_add_product_id", "2")
                startActivity(intent)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        finish()
    }
}
