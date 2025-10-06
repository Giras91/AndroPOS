package com.extrotarget.extropos.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
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
        configureButton(view, R.id.btn_cash_register, R.drawable.ic_launcher_foreground, "Cash Register")
        configureButton(view, R.id.btn_table, R.drawable.ic_launcher_foreground, "Table")
        configureButton(view, R.id.btn_report, R.drawable.ic_launcher_foreground, "Report")
        configureButton(view, R.id.btn_customers, R.drawable.ic_launcher_foreground, "Customers")
        configureButton(view, R.id.btn_settings, R.drawable.ic_launcher_foreground, "Settings")
    }

    private fun configureButton(parent: View, includeId: Int, iconRes: Int, label: String) {
        val includeRoot = parent.findViewById<View>(includeId) ?: return
        val icon = includeRoot.findViewById<ImageView>(R.id.button_icon)
        val text = includeRoot.findViewById<TextView>(R.id.button_label)
        icon.setImageResource(iconRes)
        text.text = label
        includeRoot.setOnClickListener {
            // TODO: Navigate to the respective feature fragment/activity
        }
    }
}
