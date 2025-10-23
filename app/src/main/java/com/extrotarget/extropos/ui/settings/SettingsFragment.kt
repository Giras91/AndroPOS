package com.extrotarget.extropos.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.extrotarget.extropos.R
import com.extrotarget.extropos.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint
import android.util.Log
import android.widget.Toast

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupClickListeners()
    }

    private fun setupUI() {
        binding.toolbar.title = "Settings"
        binding.toolbar.setNavigationOnClickListener {
            try {
                findNavController().navigateUp()
            } catch (e: Exception) {
                Log.e("SettingsFragment", "Navigation up failed", e)
                requireActivity().onBackPressed()
            }
        }
    }

    private fun safeNavigate(actionId: Int, actionName: String) {
        try {
            findNavController().navigate(actionId)
        } catch (e: Exception) {
            Log.e("SettingsFragment", "Navigation to $actionName failed", e)
            Toast.makeText(requireContext(), "Navigation failed: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun setupClickListeners() {
        // Printer Setup
        binding.printerSetupCard.setOnClickListener {
            safeNavigate(R.id.action_settings_to_printer_setup, "Printer Setup")
        }

        // Employee Management
        binding.employeeManagementCard.setOnClickListener {
            safeNavigate(R.id.action_settings_to_employee_management, "Employee Management")
        }

        // Inventory Management
        binding.inventoryManagementCard.setOnClickListener {
            safeNavigate(R.id.action_settings_to_inventory_management, "Inventory Management")
        }

        // Payment Settings
        binding.paymentSettingsCard.setOnClickListener {
            safeNavigate(R.id.action_settings_to_payment_settings, "Payment Settings")
        }

        // Hardware Settings
        binding.hardwareSettingsCard.setOnClickListener {
            safeNavigate(R.id.action_settings_to_hardware_settings, "Hardware Settings")
        }

        // Reporting Settings
        binding.reportingSettingsCard.setOnClickListener {
            safeNavigate(R.id.action_settings_to_reporting_settings, "Reporting Settings")
        }

        // Table Management
        binding.tableManagementCard.setOnClickListener {
            safeNavigate(R.id.action_settings_to_table_management, "Table Management")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}