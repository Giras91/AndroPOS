package com.extrotarget.extropos.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.extrotarget.extropos.databinding.FragmentMenuBinding
import com.extrotarget.extropos.domain.model.Category
import com.extrotarget.extropos.domain.model.MenuItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MenuViewModel by viewModels()
    private val productViewModel: com.extrotarget.extropos.ui.product.ProductViewModel by activityViewModels()

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var menuItemAdapter: MenuItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerViews()
        setupSearch()
        observeViewModel()
        setupAddCategory()
    }

    private fun setupAddCategory() {
        binding.addCategoryFab.setOnClickListener {
            // Show a simple dialog to collect id and name
            val ctx = requireContext()
            val container = android.widget.LinearLayout(ctx).apply {
                orientation = android.widget.LinearLayout.VERTICAL
                setPadding(16, 16, 16, 16)
            }
            val idInput = android.widget.EditText(ctx).apply {
                hint = "Category ID (e.g. 1)"
                id = com.extrotarget.extropos.R.id.dialog_category_id_input
            }
            val nameInput = android.widget.EditText(ctx).apply {
                hint = "Category name"
                id = com.extrotarget.extropos.R.id.dialog_category_name_input
            }
            container.addView(idInput)
            container.addView(nameInput)

            androidx.appcompat.app.AlertDialog.Builder(ctx)
                .setTitle("Add Category")
                .setView(container)
                .setPositiveButton("Add") { _, _ ->
                    val id = idInput.text.toString().trim()
                    val name = nameInput.text.toString().trim()
                    if (id.isNotBlank() && name.isNotBlank()) {
                        // Add category using the shared activity ProductViewModel
                        productViewModel.addCategory(id, name)
                    }
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }

    private fun setupRecyclerViews() {
        // Categories horizontal list
        categoryAdapter = CategoryAdapter { category ->
            viewModel.selectCategory(category)
        }
        binding.categoriesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapter
        }

        // Menu items grid
        menuItemAdapter = MenuItemAdapter { menuItem ->
            // Handle menu item click - add to cart
            showAddToCartDialog(menuItem)
        }
        binding.menuItemsRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = menuItemAdapter
        }
    }

    private fun setupSearch() {
        binding.searchEditText.setOnEditorActionListener { textView, _, _ ->
            val query = textView.text.toString()
            if (query.isNotEmpty()) {
                viewModel.searchMenuItems(query)
            } else {
                viewModel.clearSearch()
            }
            true
        }

        binding.clearSearchButton.setOnClickListener {
            binding.searchEditText.text.clear()
            viewModel.clearSearch()
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.categories.collect { categories ->
                        categoryAdapter.submitList(categories)
                    }
                }

                launch {
                    viewModel.menuItems.collect { menuItems ->
                        menuItemAdapter.submitList(menuItems)
                        binding.emptyStateTextView.visibility =
                            if (menuItems.isEmpty()) View.VISIBLE else View.GONE
                    }
                }

                launch {
                    viewModel.isLoading.collect { isLoading ->
                        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
                    }
                }

                launch {
                    viewModel.error.collect { error ->
                        error?.let {
                            // Show error message
                            binding.errorTextView.text = it
                            binding.errorTextView.visibility = View.VISIBLE
                        } ?: run {
                            binding.errorTextView.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun showAddToCartDialog(menuItem: MenuItem) {
        // TODO: Implement add to cart dialog
        // This would show quantity selector and add to order
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}