package com.extrotarget.extropos.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.extrotarget.extropos.R
import com.extrotarget.extropos.databinding.FragmentTablesBinding
import com.extrotarget.extropos.domain.model.Table
import com.extrotarget.extropos.domain.model.TableSection
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController
import com.extrotarget.extropos.ui.main.TablesFragmentDirections
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import androidx.fragment.app.activityViewModels
import com.extrotarget.extropos.ui.order.OrderViewModel

/**
 * Fragment for table selection in the main ordering workflow.
 * Displays tables organized by sections with status indicators.
 */
@AndroidEntryPoint
class TablesFragment : Fragment() {

    private var _binding: FragmentTablesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TableSelectionViewModel by viewModels()
    private val orderViewModel: OrderViewModel by activityViewModels()
    private lateinit var tableAdapter: TableSelectionAdapter
    private lateinit var sectionAdapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTablesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSectionSpinner()
        observeViewModel()
        loadData()
    }

    private fun setupRecyclerView() {
        tableAdapter = TableSelectionAdapter { table ->
            onTableSelected(table)
        }

        binding.tablesRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 4)
            adapter = tableAdapter
        }
    }

    private fun setupSectionSpinner() {
        sectionAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            mutableListOf("All Sections")
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        binding.sectionSpinner.adapter = sectionAdapter
        binding.sectionSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedSection = if (position == 0) null else viewModel.sections.value.getOrNull(position - 1)
                viewModel.selectSection(selectedSection)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.selectSection(null)
            }
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.sections.collect { sections ->
                updateSectionSpinner(sections)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.filteredTables.collect { tables ->
                tableAdapter.submitList(tables)
            }
        }
    }

    private fun updateSectionSpinner(sections: List<TableSection>) {
        val sectionNames = mutableListOf("All Sections")
        sectionNames.addAll(sections.map { it.name })
        sectionAdapter.clear()
        sectionAdapter.addAll(sectionNames)
        sectionAdapter.notifyDataSetChanged()
    }

    private fun loadData() {
        viewModel.loadData()
    }

    private fun onTableSelected(table: Table) {
        // Create an order for the selected table via shared OrderViewModel, then navigate to editor
        try {
            // Observe one-time creation: wait until currentOrder with matching tableId is emitted
            viewLifecycleOwner.lifecycleScope.launch {
                    val order = orderViewModel.currentOrder
                        .filter { it != null }
                        .first()

                    order?.let {
                        // Build the action and set the orderId (generated action has setters)
                        val action = TablesFragmentDirections.actionTablesToOrderEditor().setOrderId(it.id)
                        findNavController().navigate(action)
                    }
            }

            // Trigger order creation
            orderViewModel.createNewOrder(table.id)
        } catch (e: Exception) {
            android.widget.Toast.makeText(requireContext(), "Selected table: T${table.number}", android.widget.Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
