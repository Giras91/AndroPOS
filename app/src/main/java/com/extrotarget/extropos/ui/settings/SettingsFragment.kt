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
            findNavController().navigateUp()
        }
    }

    private fun setupClickListeners() {
        // Printer Setup
        binding.printerSetupCard.setOnClickListener {
            findNavController().navigate(R.id.action_settings_to_printer_setup)
        }

        // Employee Management
        binding.employeeManagementCard.setOnClickListener {
            findNavController().navigate(R.id.action_settings_to_employee_management)
        }

        // Inventory Management
        binding.inventoryManagementCard.setOnClickListener {
            findNavController().navigate(R.id.action_settings_to_inventory_management)
        }

        // Payment Settings
        binding.paymentSettingsCard.setOnClickListener {
            findNavController().navigate(R.id.action_settings_to_payment_settings)
        }

        // Hardware Settings
        binding.hardwareSettingsCard.setOnClickListener {
            findNavController().navigate(R.id.action_settings_to_hardware_settings)
        }

        // Reporting Settings
        binding.reportingSettingsCard.setOnClickListener {
            findNavController().navigate(R.id.action_settings_to_reporting_settings)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}