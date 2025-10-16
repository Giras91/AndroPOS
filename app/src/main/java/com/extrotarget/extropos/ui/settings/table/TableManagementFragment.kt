package com.extrotarget.extropos.ui.settings.table

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.extrotarget.extropos.R
import com.extrotarget.extropos.databinding.FragmentTableManagementBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TableManagementFragment : Fragment() {

    private var _binding: FragmentTableManagementBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTableManagementBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupClickListeners()
    }

    private fun setupUI() {
        binding.toolbar.title = "Table Management"
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupClickListeners() {
        // Table Configuration
        binding.tableConfigurationCard.setOnClickListener {
            findNavController().navigate(R.id.action_table_management_to_table_configuration)
        }

        // Table Sections
        binding.tableSectionsCard.setOnClickListener {
            findNavController().navigate(R.id.action_table_management_to_table_sections)
        }

        // Table Layouts
        binding.tableLayoutsCard.setOnClickListener {
            findNavController().navigate(R.id.action_table_management_to_table_layouts)
        }

        // Reservations
        binding.reservationsCard.setOnClickListener {
            findNavController().navigate(R.id.action_table_management_to_reservations)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}