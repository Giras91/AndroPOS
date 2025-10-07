package com.extrotarget.extropos.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.graphics.drawable.GradientDrawable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import android.util.Log
import com.extrotarget.extropos.R

/**
 * Dashboard styled after Genius POS but branded as ExtroPOS.
 */
class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Use styled text/emoji for tiles
        configureButton(view, R.id.btn_cash_register, "💰", "Cash Register", R.color.tile_blue)
        configureButton(view, R.id.btn_table, "🪑", "Table", R.color.tile_orange)
        configureButton(view, R.id.btn_report, "📊", "Report", R.color.tile_teal)
        configureButton(view, R.id.btn_customers, "👥", "Customers", R.color.tile_purple)
        configureButton(view, R.id.btn_settings, "⚙️", "Settings", R.color.tile_cyan)
        Log.i("DashboardDebug", "onViewCreated: DashboardFragment view created and buttons configured")
    }

    private fun configureButton(parent: View, includeId: Int, emoji: String, label: String, colorRes: Int) {
        val includeRoot = parent.findViewById<View>(includeId) ?: return
        
        val emojiView = includeRoot.findViewById<TextView?>(R.id.button_emoji)
        val labelView = includeRoot.findViewById<TextView?>(R.id.button_label)
        
        emojiView?.text = emoji
        labelView?.text = label
        
        val card = includeRoot as? MaterialCardView
        if (card != null) {
            try {
                card.setCardBackgroundColor(ContextCompat.getColor(requireContext(), colorRes))
                emojiView?.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white))
                labelView?.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white))
            } catch (_: Exception) { }
        }
        
        includeRoot.setOnClickListener {
            Log.i("DashboardDebug", "click handler invoked for view id=$includeId")
            activity?.findViewById<TextView>(com.extrotarget.extropos.R.id.actionStatus)?.text = "Clicked: $label"
            try {
                val navController = findNavController()
                when (includeId) {
                    R.id.btn_cash_register -> {
                        Log.i("DashboardDebug", "clicked: Cash Register -> navigating to POS")
                        navController.navigate(R.id.action_main_to_pos)
                    }
                    R.id.btn_settings -> {
                        Log.i("DashboardDebug", "clicked: Settings -> navigating to Settings")
                        navController.navigate(R.id.action_main_to_settings)
                    }
                    R.id.btn_report -> {
                        Log.i("DashboardDebug", "clicked: Report -> navigating to Settings (reporting placeholder)")
                        navController.navigate(R.id.action_main_to_settings)
                    }
                    R.id.btn_customers -> {
                        Log.i("DashboardDebug", "clicked: Customers -> navigating to Settings (placeholder)")
                        navController.navigate(R.id.action_main_to_settings)
                    }
                    R.id.btn_table -> {
                        Log.i("DashboardDebug", "clicked: Table -> navigating to Tables or fallback to POS")
                        val actionId = resources.getIdentifier("action_main_to_tables", "id", requireContext().packageName)
                        if (actionId != 0) {
                            navController.navigate(actionId)
                        } else {
                            navController.navigate(R.id.action_main_to_pos)
                        }
                    }
                }
            } catch (ex: Exception) {
                Log.w("DashboardDebug", "click handler failed, fallback (exception): ${ex.message}")
            }
        }
    }
}
