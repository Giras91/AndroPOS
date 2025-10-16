package com.extrotarget.extropos.ui.settings.table

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.extrotarget.extropos.R
import com.extrotarget.extropos.databinding.FragmentTableLayoutsBinding
import com.extrotarget.extropos.domain.model.TableLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class TableLayoutsFragment : Fragment() {
    private var _binding: FragmentTableLayoutsBinding? = null
    private val binding get() = _binding!!

    private val vm: TableLayoutsViewModel by viewModels()
    private lateinit var adapter: TableLayoutsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTableLayoutsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupList()
        setupFab()
        observeState()
    }

    private fun setupToolbar() {
        binding.toolbar.title = getString(R.string.table_layouts)
        binding.toolbar.setNavigationOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }
    }

    private fun setupList() {
        adapter = TableLayoutsAdapter(
            items = emptyList(),
            onEdit = { showDialog(it) },
            onDelete = { confirmDelete(it) },
            onSetDefault = { vm.setDefault(it.id) }
        )
        binding.recycler.adapter = adapter
    }

    private fun setupFab() {
        binding.fab.setOnClickListener { showDialog(null) }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            vm.layouts.collectLatest { list ->
                adapter.submit(list)
                binding.empty.isVisible = list.isEmpty()
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            vm.isLoading.collectLatest { binding.progress.isVisible = it }
        }
    }

    private fun showDialog(existing: TableLayout?) {
        val dialog = TableLayoutDialogFragment.newInstance(existing)
        dialog.onResult = { vm.save(it) }
        dialog.show(childFragmentManager, "layoutDialog")
    }

    private fun confirmDelete(item: TableLayout) {
        // Simple immediate delete for now
        vm.delete(item.id)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_table_layouts, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_add) {
            showDialog(null)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
