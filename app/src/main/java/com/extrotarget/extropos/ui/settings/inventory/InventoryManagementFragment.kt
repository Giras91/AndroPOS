package com.extrotarget.extropos.ui.settings.inventory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.extrotarget.extropos.databinding.FragmentInventoryManagementBinding
import androidx.fragment.app.activityViewModels
import com.extrotarget.extropos.ui.product.ProductViewModel
import com.extrotarget.extropos.domain.model.Product

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

        // Default: show product FAB when Products tab selected, category FAB when Categories selected
        binding.addProductFab?.visibility = View.VISIBLE
        binding.addCategoryFab?.visibility = View.GONE

        binding.inventoryTabs.addOnTabSelectedListener(object : com.google.android.material.tabs.TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: com.google.android.material.tabs.TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> { // Products
                        binding.addProductFab?.visibility = View.VISIBLE
                        binding.addCategoryFab?.visibility = View.GONE
                    }
                    1 -> { // Categories
                        binding.addProductFab?.visibility = View.GONE
                        binding.addCategoryFab?.visibility = View.VISIBLE
                    }
                    else -> {
                        binding.addProductFab?.visibility = View.GONE
                        binding.addCategoryFab?.visibility = View.GONE
                    }
                }
            }

            override fun onTabUnselected(tab: com.google.android.material.tabs.TabLayout.Tab?) {}
            override fun onTabReselected(tab: com.google.android.material.tabs.TabLayout.Tab?) {}
        })

        // Wire fab actions
        setupAddCategoryFab()
        setupAddProductFab()
        // TODO: Implement tab content switching
    }

    private val productViewModel: ProductViewModel by activityViewModels()

    private fun setupAddCategoryFab() {
        try {
            binding.addCategoryFab.setOnClickListener {
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
                            productViewModel.addCategory(id, name)
                        }
                    }
                    .setNegativeButton("Cancel", null)
                    .show()
            }
        } catch (e: Exception) {
            // ignore if view missing
        }
    }

    private fun setupAddProductFab() {
        try {
            binding.addProductFab.setOnClickListener {
                val ctx = requireContext()
                val container = android.widget.LinearLayout(ctx).apply {
                    orientation = android.widget.LinearLayout.VERTICAL
                    setPadding(16, 16, 16, 16)
                }
                val idInput = android.widget.EditText(ctx).apply {
                    hint = "Product ID (unique)"
                    id = com.extrotarget.extropos.R.id.dialog_product_id_input
                }
                val nameInput = android.widget.EditText(ctx).apply {
                    hint = "Product name"
                    id = com.extrotarget.extropos.R.id.dialog_product_name_input
                }
                val priceInput = android.widget.EditText(ctx).apply {
                    hint = "Price (RM, e.g. 3.50)"
                    id = com.extrotarget.extropos.R.id.dialog_product_price_input
                }
                val categoryInput = android.widget.EditText(ctx).apply {
                    hint = "Category ID (e.g. 1)"
                    id = com.extrotarget.extropos.R.id.dialog_product_category_input
                }
                container.addView(idInput)
                container.addView(nameInput)
                container.addView(priceInput)
                container.addView(categoryInput)

                androidx.appcompat.app.AlertDialog.Builder(ctx)
                    .setTitle("Add Product")
                    .setView(container)
                    .setPositiveButton("Add") { _, _ ->
                        val id = idInput.text.toString().trim()
                        val name = nameInput.text.toString().trim()
                        val priceText = priceInput.text.toString().trim()
                        val categoryId = categoryInput.text.toString().trim().ifBlank { "0" }
                        if (id.isNotBlank() && name.isNotBlank() && priceText.isNotBlank()) {
                            val priceCents = try {
                                (priceText.replace(",", "").toDouble() * 100).toLong()
                            } catch (e: Exception) { 0L }

                            val product = Product(
                                id = id,
                                name = name,
                                description = "",
                                priceCents = priceCents,
                                categoryId = categoryId,
                                isAvailable = true
                            )

                            productViewModel.addProduct(product)
                        }
                    }
                    .setNegativeButton("Cancel", null)
                    .show()
            }
        } catch (e: Exception) {
            // ignore
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}