package com.extrotarget.extropos.ui.dashboard

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.content.Intent
import com.extrotarget.extropos.MainActivity
import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView
import com.extrotarget.extropos.R
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    private val debugSimulateReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == "com.extrotarget.extropos.debug.SIMULATE_CASH_CLICK") {
                try {
                    val status = findViewById<TextView>(R.id.actionStatus)
                    status?.text = "Simulated Cash Click"
                    // Reuse the same behavior as the Cash Register click: open MainActivity -> POS
                    val mainIntent = Intent(this@DashboardActivity, com.extrotarget.extropos.MainActivity::class.java)
                    mainIntent.putExtra("debug_open_dashboard", true)
                    mainIntent.putExtra("debug_open_pos", true)
                    startActivity(mainIntent)
                    finish()
                } catch (e: Exception) {
                    Log.w("DashboardDebug", "simulate receiver failed: ${e.message}")
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // By default, redirect to MainActivity which hosts the newer fragment-based dashboard.
        // Keep an escape hatch: if the caller explicitly requests the legacy dashboard via
        // the extra "force_show_old_dashboard"==true, we will render the old activity UI.
        val forceOld = intent?.getBooleanExtra("force_show_old_dashboard", false) == true
        if (!forceOld) {
            try {
                val mainIntent = Intent(this, MainActivity::class.java)
                // preserve debug flags if present
                if (intent?.getBooleanExtra("debug_open_pos", false) == true) {
                    mainIntent.putExtra("debug_open_pos", true)
                }
                if (intent?.getBooleanExtra("debug_open_dashboard", false) == true) {
                    mainIntent.putExtra("debug_open_dashboard", true)
                }
                startActivity(mainIntent)
                finish()
                return
            } catch (e: Exception) {
                // If redirection fails for some reason, fall back to rendering the legacy UI.
                e.printStackTrace()
            }
        }

        // Load the dashboard UI so the (legacy) activity remains visible.
        setContentView(com.extrotarget.extropos.R.layout.activity_dashboard)

        // Small confirmation for debugging — will be removed or converted to UI state later.
        Toast.makeText(this, "Dashboard Activity (legacy) Loaded", Toast.LENGTH_SHORT).show()

        // Configure tiles (emoji, label, color) and attach basic click logging
        try {
            val status = findViewById<TextView>(R.id.actionStatus)

            configureButton(R.id.btn_cash_register, "💰", "Cash Register", R.color.tile_blue) {
                // Launch the main app and request the POS screen so the user is taken
                // directly to the Cash Register (POS) flow.
                try {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("debug_open_dashboard", true)
                    intent.putExtra("debug_open_pos", true)
                    startActivity(intent)
                    finish()
                } catch (e: Exception) {
                    status?.text = "Cash Register"
                    Log.w("DashboardDebug", "Failed to open MainActivity for POS: ${e.message}")
                }
            }
            configureButton(R.id.btn_table, "🪑", "Table", R.color.tile_orange) {
                status?.text = "Tables"
            }
            configureButton(R.id.btn_report, "📊", "Report", R.color.tile_teal) {
                status?.text = "Report"
            }
            configureButton(R.id.btn_customers, "👥", "Customers", R.color.tile_purple) {
                status?.text = "Customers"
            }
            configureButton(R.id.btn_settings, "⚙️", "Settings", R.color.tile_cyan) {
                status?.text = "Settings"
            }

            val fab = findViewById<android.view.View>(R.id.fabPdf)
            fab?.setOnClickListener {
                Log.i("DashboardDebug", "Activity: PDF FAB clicked")
                status?.text = "PDF"
            }
        } catch (ex: Exception) {
            Log.w("DashboardDebug", "failed to attach activity listeners: ${ex.message}")
        }

        // Register debug broadcast for simulating a cash-register click (debug-only)
        try {
            registerReceiver(debugSimulateReceiver, IntentFilter("com.extrotarget.extropos.debug.SIMULATE_CASH_CLICK"))
        } catch (_: Exception) { }

    }

    private fun configureButton(includeId: Int, emoji: String, label: String, colorRes: Int, onClick: (() -> Unit)? = null) {
        val includeRoot = findViewById<android.view.View>(includeId) ?: return
        val emojiView = includeRoot.findViewById<TextView>(R.id.button_emoji)
        val labelView = includeRoot.findViewById<TextView>(R.id.button_label)
        
        emojiView?.text = emoji
        labelView?.text = label

        val card = includeRoot as? MaterialCardView
        if (card != null) {
            try {
                val color = ContextCompat.getColor(this, colorRes)
                card.setCardBackgroundColor(color)
                emojiView?.setTextColor(ContextCompat.getColor(this, android.R.color.white))
                labelView?.setTextColor(ContextCompat.getColor(this, android.R.color.white))
            } catch (_: Exception) { }
        }

        includeRoot.setOnClickListener {
            Log.i("DashboardDebug", "Activity: clicked includeId=$includeId label=$label")
            onClick?.invoke()
        }
    }
}
