package com.extrotarget.extropos.ui.settings.table

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.extrotarget.extropos.databinding.FragmentTableSectionsBinding
import com.extrotarget.extropos.domain.model.TableSection
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TableSectionsFragment : Fragment() {

    private var _binding: FragmentTableSectionsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TableSectionsViewModel by viewModels()
    private lateinit var adapter: TableSectionsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTableSectionsBinding.inflate(inflater, container, false)
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
        binding.toolbar.setNavigationOnClickListener { parentFragmentManager.popBackStack() }
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                com.extrotarget.extropos.R.id.action_add_section -> { showAddEditDialog(null); true }
                else -> false
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = TableSectionsAdapter(
            onEditClick = { section -> showAddEditDialog(section) },
            onDeleteClick = { section -> confirmDelete(section) }
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun setupFab() { binding.fabAddSection.setOnClickListener { showAddEditDialog(null) } }

    private fun setupSwipeRefresh() { binding.swipeRefreshLayout.setOnRefreshListener { viewModel.load() } }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.sections.collect { list ->
                adapter.submitList(list)
                updateEmptyState(list.isEmpty())
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isLoading.collect { loading ->
                binding.progressBar.visibility = if (loading && !binding.swipeRefreshLayout.isRefreshing) View.VISIBLE else View.GONE
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.error.collect { err -> err?.let { Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show() } }
        }
    }

    private fun updateEmptyState(isEmpty: Boolean) {
        binding.emptyStateLayout.visibility = if (isEmpty) View.VISIBLE else View.GONE
        binding.recyclerView.visibility = if (isEmpty) View.GONE else View.VISIBLE
        binding.btnAddFirstSection.setOnClickListener { showAddEditDialog(null) }
    }

    private fun confirmDelete(section: TableSection) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Delete Section")
            .setMessage("Delete ${section.name}? This won't remove tables but will unlink the section.")
            .setPositiveButton("Delete") { _, _ -> viewModel.delete(section.id) }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showAddEditDialog(section: TableSection?) {
        val dialog = TableSectionDialogFragment.newInstance(section)
        dialog.onSaved = { saved -> viewModel.save(saved) }
        dialog.show(parentFragmentManager, "section_dialog")
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
