package com.extrotarget.extropos.ui.settings.table

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.extrotarget.extropos.R
import com.extrotarget.extropos.databinding.FragmentTableConfigurationBinding
import com.extrotarget.extropos.domain.model.Table
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TableConfigurationFragment : Fragment() {

    private var _binding: FragmentTableConfigurationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TableConfigurationViewModel by viewModels()
    private lateinit var adapter: TableConfigurationAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTableConfigurationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupRecyclerView()
        setupFab()
        observeViewModel()
        setupSwipeRefresh()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_add_table -> {
                    showAddTableDialog()
                    true
                }
                else -> false
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = TableConfigurationAdapter(
            onEditClick = { table -> showEditTableDialog(table) },
            onDeleteClick = { table -> showDeleteConfirmationDialog(table) }
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@TableConfigurationFragment.adapter
        }
    }

    private fun setupFab() {
        binding.fabAddTable.setOnClickListener {
            showAddTableDialog()
        }
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadTables()
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.tables.collect { tables ->
                adapter.submitList(tables)
                updateEmptyState(tables.isEmpty())
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isLoading.collect { isLoading ->
                binding.progressBar.visibility = if (isLoading && !binding.swipeRefreshLayout.isRefreshing) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.error.collect { error ->
                error?.let {
                    Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                    viewModel.clearError()
                }
            }
        }
    }

    private fun updateEmptyState(isEmpty: Boolean) {
        binding.emptyStateLayout.visibility = if (isEmpty) View.VISIBLE else View.GONE
        binding.recyclerView.visibility = if (isEmpty) View.GONE else View.VISIBLE

        binding.btnAddFirstTable.setOnClickListener {
            showAddTableDialog()
        }
    }

    private fun showAddTableDialog() {
        val dialog = TableConfigurationDialogFragment.newInstance(null)
        dialog.setTargetFragment(this, 0)
        dialog.show(parentFragmentManager, "add_table")
    }

    private fun showEditTableDialog(table: Table) {
        val dialog = TableConfigurationDialogFragment.newInstance(table)
        dialog.setTargetFragment(this, 0)
        dialog.show(parentFragmentManager, "edit_table")
    }

    private fun showDeleteConfirmationDialog(table: Table) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Delete Table")
            .setMessage("Are you sure you want to delete Table ${table.number}? This action cannot be undone.")
            .setPositiveButton("Delete") { _, _ ->
                viewModel.deleteTable(table.id)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    // Called by TableConfigurationDialogFragment when a table is saved
    fun onTableSaved(table: Table) {
        if (table.id.isBlank()) {
            // New table
            viewModel.addTable(table.copy(id = generateTableId()))
        } else {
            // Update existing table
            viewModel.updateTable(table)
        }
    }

    private fun generateTableId(): String {
        return "table_${System.currentTimeMillis()}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}