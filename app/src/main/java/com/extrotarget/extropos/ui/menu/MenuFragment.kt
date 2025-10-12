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
    
    // Flag to control whether the internal FAB should be shown
    private var showInternalFab = true

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
        // Hide the FAB if requested (e.g., when used inside InventoryManagement)
        if (!showInternalFab) {
            binding.addCategoryFab.visibility = View.GONE
            return
        }
        
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
                        android.util.Log.d("MenuFragment", "Adding category: id=$id, name=$name")
                        productViewModel.addCategory(id, name)
                        
                        // Reload categories in MenuViewModel to show the new category
                        android.util.Log.d("MenuFragment", "Reloading categories in MenuViewModel")
                        viewModel.loadCategories()
                        
                        android.util.Log.d("MenuFragment", "Category add process completed")
                    } else {
                        android.util.Log.d("MenuFragment", "Category add failed: empty id or name")
                    }
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }

    private fun setupRecyclerViews() {
        // Categories horizontal list
        categoryAdapter = CategoryAdapter(
            onCategoryClick = { category ->
                viewModel.selectCategory(category)
            },
            onCategoryLongClick = { category ->
                showEditCategoryDialog(category)
            }
        )
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

    private fun showEditCategoryDialog(category: Category) {
        val ctx = requireContext()
        
        // Create options dialog: Edit or Delete
        val options = arrayOf("Edit Category", "Delete Category")
        androidx.appcompat.app.AlertDialog.Builder(ctx)
            .setTitle(category.name)
            .setItems(options) { _, which ->
                when (which) {
                    0 -> showEditCategoryFormDialog(category)
                    1 -> showDeleteCategoryConfirmDialog(category)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showEditCategoryFormDialog(category: Category) {
        val ctx = requireContext()
        val container = android.widget.LinearLayout(ctx).apply {
            orientation = android.widget.LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }

        val nameEditText = android.widget.EditText(ctx).apply {
            hint = "Category Name"
            setText(category.name)
        }
        val descriptionEditText = android.widget.EditText(ctx).apply {
            hint = "Description"
            setText(category.description)
        }

        container.addView(nameEditText)
        container.addView(descriptionEditText)

        androidx.appcompat.app.AlertDialog.Builder(ctx)
            .setTitle("Edit Category")
            .setView(container)
            .setPositiveButton("Update") { _, _ ->
                val name = nameEditText.text.toString().trim()
                val description = descriptionEditText.text.toString().trim()

                if (name.isNotBlank()) {
                    val updatedCategory = category.copy(
                        name = name,
                        description = description
                    )
                    viewModel.updateCategory(updatedCategory)
                    android.util.Log.d("MenuFragment", "Category update process completed")
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showDeleteCategoryConfirmDialog(category: Category) {
        val ctx = requireContext()
        androidx.appcompat.app.AlertDialog.Builder(ctx)
            .setTitle("Delete Category")
            .setMessage("Are you sure you want to delete \"${category.name}\"?\n\nThis action cannot be undone.")
            .setPositiveButton("Delete") { _, _ ->
                viewModel.deleteCategory(category.id)
                android.util.Log.d("MenuFragment", "Category delete process completed")
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    /**
     * Control whether the internal FAB should be shown.
     * Useful when this fragment is embedded in another screen that has its own FABs.
     */
    fun hideInternalFab(hide: Boolean) {
        showInternalFab = !hide
        if (_binding != null) {
            binding.addCategoryFab.visibility = if (showInternalFab) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}