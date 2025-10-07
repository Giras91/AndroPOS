package com.extrotarget.extropos.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView
import android.util.Log
import com.extrotarget.extropos.R
import com.extrotarget.extropos.databinding.FragmentMainBinding
import com.extrotarget.extropos.ui.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observeAuthState()
    }

    private fun setupUI() {
        // Configure dashboard tiles (same look+feel as DashboardFragment)
        val root = requireView()
        configureButton(root, R.id.btn_cash_register, "💰", "Cash Register", R.color.tile_blue)
        configureButton(root, R.id.btn_table, "🪑", "Table", R.color.tile_orange)
        configureButton(root, R.id.btn_report, "📊", "Report", R.color.tile_teal)
        configureButton(root, R.id.btn_customers, "👥", "Customers", R.color.tile_purple)
        configureButton(root, R.id.btn_settings, "⚙️", "Settings", R.color.tile_cyan)
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
            Log.i("DashboardDebug", "MainFragment click handler invoked for view id=$includeId")
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
                        Log.i("DashboardDebug", "clicked: Report -> navigating to Reporting")
                        navController.navigate(R.id.action_main_to_reporting)
                    }
                    R.id.btn_customers -> {
                        Log.i("DashboardDebug", "clicked: Customers -> navigating to Customers")
                        navController.navigate(R.id.action_main_to_customers)
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
                Log.w("DashboardDebug", "MainFragment click handler failed, fallback (exception): ${ex.message}")
            }
        }
    }

    private fun observeAuthState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    authViewModel.authState.collect { state ->
                        // Handle auth state changes if needed
                        // For now, just display welcome message
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}