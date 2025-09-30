package com.extrotarget.extropos.ui.settings.inventory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.extrotarget.extropos.databinding.FragmentInventoryManagementBinding

class InventoryManagementFragment : Fragment() {

    private var _binding: FragmentInventoryManagementBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInventoryManagementBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    private fun setupUI() {
        binding.toolbar.title = "Inventory Management"
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        // Setup tabs for different inventory sections
        binding.inventoryTabs.addTab(binding.inventoryTabs.newTab().setText("Products"))
        binding.inventoryTabs.addTab(binding.inventoryTabs.newTab().setText("Categories"))
        binding.inventoryTabs.addTab(binding.inventoryTabs.newTab().setText("Stock Levels"))
        binding.inventoryTabs.addTab(binding.inventoryTabs.newTab().setText("Barcodes"))

        // TODO: Implement tab content switching
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}