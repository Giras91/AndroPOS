package com.extrotarget.extropos.ui.product

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.extrotarget.extropos.databinding.FragmentProductsGridBinding
import com.extrotarget.extropos.domain.model.CartItem
import com.extrotarget.extropos.domain.model.Product
import com.extrotarget.extropos.ui.cart.CartViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.first
import android.util.Log

@AndroidEntryPoint
class ProductsGridFragment : Fragment() {

    private var _binding: FragmentProductsGridBinding? = null
    private val binding get() = _binding!!

    private val productViewModel: ProductViewModel by activityViewModels()
    private val cartViewModel: CartViewModel by activityViewModels()

    private lateinit var productsAdapter: ProductsAdapter
    
    // Flag to control whether the internal FAB should be shown
    private var showInternalFab = true
    
    // Flag to control behavior: true = management mode (edit products), false = selling mode (add to cart)
    private var isManagementMode = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // ViewModels are injected via Hilt
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsGridBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSearch()
        observeViewModel()
        loadProducts()
        setupAddProduct()
    }

    private fun setupAddProduct() {
        // If layout doesn't include the FAB (older builds), skip
        try {
            // Hide the FAB if requested (e.g., when used inside InventoryManagement)
            if (!showInternalFab) {
                binding.addProductFab.visibility = View.GONE
                return
            }
            
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
                val categoryLabel = android.widget.TextView(ctx).apply {
                    text = "Category:"
                    textSize = 14f
                    setPadding(0, 16, 0, 8)
                }
                
                val categorySpinner = android.widget.Spinner(ctx).apply {
                    id = com.extrotarget.extropos.R.id.dialog_product_category_input
                }
                
                // Populate category spinner
                val categories = productViewModel.getCategories()
                val categoryOptions = mutableListOf("Select Category")
                val categoryIds = mutableListOf("")
                
                categories.forEach { (id, name) ->
                    categoryOptions.add("$name ($id)")
                    categoryIds.add(id)
                }
                
                val adapter = android.widget.ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, categoryOptions)
                categorySpinner.adapter = adapter
                container.addView(idInput)
                container.addView(nameInput)
                container.addView(priceInput)
                container.addView(categoryLabel)
                container.addView(categorySpinner)

                androidx.appcompat.app.AlertDialog.Builder(ctx)
                    .setTitle("Add Product")
                    .setView(container)
                    .setPositiveButton("Add") { _, _ ->
                        val id = idInput.text.toString().trim()
                        val name = nameInput.text.toString().trim()
                        val priceText = priceInput.text.toString().trim()
                        val selectedPosition = categorySpinner.selectedItemPosition
                        val categoryId = if (selectedPosition > 0) categoryIds[selectedPosition] else ""
                        
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
            // No FAB in layout or binding missing, ignore
        }
    }

    // Called by parent fragment to set search query programmatically
    fun setSearchQuery(query: String) {
        productViewModel.searchProducts(query)
    }

    // Called by parent fragment to filter by categoryId (null or blank = all)
    fun filterByCategory(categoryId: String?) {
        productViewModel.filterByCategory(categoryId)
    }

    // Return a list of available categories (simple set from current products)
    fun getAvailableCategories(): List<Pair<String, String>> {
        return productViewModel.getCategories()
    }

    // Return a product by id if present in the loaded dataset
    fun getProductById(id: String): Product? {
        return productViewModel.getProductById(id)
    }

    // Allow hiding the internal search EditText if parent provides one
    fun hideInternalSearch(hide: Boolean) {
        if (_binding == null) return
        _binding!!.searchEditText.visibility = if (hide) View.GONE else View.VISIBLE
    }

    // Debug helper: run a search query and log the resulting product count.
    fun debugRunSearch(query: String) {
        // Set the search query which updates the viewmodel
        setSearchQuery(query)

        // Collect the latest products once and log the size
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val list = productViewModel.products.first()
                Log.i("DashboardDebug", "DebugSearch result for '$query': ${list.size} items")
            } catch (e: Exception) {
                Log.i("DashboardDebug", "DebugSearch failed: ${'$'}{e.message}")
            }
        }
    }

    private fun setupRecyclerView() {
        productsAdapter = ProductsAdapter(
            onProductClick = { product ->
            Log.d("ProductsGrid", "Product clicked: ${product.name}, managementMode: $isManagementMode")
            
            if (isManagementMode) {
                // Management mode: show edit product dialog
                showEditProductDialog(product)
            } else {
                // Selling mode: directly add to cart (simplify the logic)
                Log.d("ProductsGrid", "Selling mode - adding product to cart")
                addProductToCart(product)
            }
        },
            getCategoryName = { categoryId ->
                productViewModel.getCategories().find { it.first == categoryId }?.second ?: ""
            }
        )

        binding.productsRecyclerView.apply {
            adapter = productsAdapter
            
            // Set up consistent grid layout that will be configured after layout
            val gridLayoutManager = GridLayoutManager(requireContext(), 2) // Default 2 columns, will be updated
            layoutManager = gridLayoutManager
            
            // Apply consistent spacing in px (12dp)
            val density = resources.displayMetrics.density
            val spacingPx = (12 * density).toInt()
            addItemDecoration(GridSpacingItemDecoration(spacingPx))
            
            // Configure responsive grid after the RecyclerView is laid out
            post {
                setupResponsiveGrid(gridLayoutManager)
            }
        }
    }

    private fun setupResponsiveGrid(gridLayoutManager: GridLayoutManager) {
        val recyclerView = binding.productsRecyclerView
        val availableWidth = recyclerView.width
        
        if (availableWidth <= 0) {
            // RecyclerView not yet laid out, retry after short delay
            recyclerView.postDelayed({
                setupResponsiveGrid(gridLayoutManager)
            }, 50)
            return
        }
        
        val density = resources.displayMetrics.density
        
        // Consistent tile specifications
        val desiredTileWidthDp = 160f  // Consistent tile width in DP
        val spacingDp = 12f            // Spacing between tiles
        val paddingDp = 8f             // Container padding
        
        // Convert to pixels
        val desiredTileWidthPx = (desiredTileWidthDp * density).toInt()
        val spacingPx = (spacingDp * density).toInt()
        val paddingPx = (paddingDp * density).toInt()
        
        // Calculate optimal columns based on available width
        // Formula: availableWidth = (columns * tileWidth) + ((columns - 1) * spacing) + (2 * padding)
        val effectiveWidth = availableWidth - (2 * paddingPx)
        val columns = ((effectiveWidth + spacingPx) / (desiredTileWidthPx + spacingPx)).coerceAtLeast(1)
        
        Log.d("ProductsGrid", "Grid setup - Available: ${availableWidth}px, Columns: $columns, TileWidth: ${desiredTileWidthDp}dp")
        
        // Update the grid layout
        gridLayoutManager.spanCount = columns
    }

    private fun setupSearch() {
        binding.searchEditText.addTextChangedListener { text ->
            val query = text?.toString() ?: ""
            productViewModel.searchProducts(query)
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    productViewModel.products.collect { products ->
                        productsAdapter.submitList(products)
                    }
                }
            }
        }
    }

    private fun loadProducts() {
        productViewModel.loadProducts()
    }

    private fun addProductToCart(product: Product) {
        Log.d("ProductsGrid", "Adding product to cart: ${product.name}, stock: ${product.stockQuantity}")
        
        // Temporarily disable stock check for POS functionality - TODO: Implement proper inventory management
        // if (product.stockQuantity <= 0) {
        //     Log.w("ProductsGrid", "Product out of stock: ${product.name}")
        //     // Show out of stock message
        //     val ctx = requireContext()
        //     androidx.appcompat.app.AlertDialog.Builder(ctx)
        //         .setTitle("Out of Stock")
        //         .setMessage("${product.name} is currently out of stock.")
        //         .setPositiveButton("OK", null)
        //         .show()
        //     return
        // }

        cartViewModel.addItem(product.id, product.name, product.priceCents, 1)
        Log.d("ProductsGrid", "Successfully added ${product.name} to cart")
        
        // Show added to cart feedback
        val ctx = requireContext()
        android.widget.Toast.makeText(ctx, "Added ${product.name} to cart", android.widget.Toast.LENGTH_SHORT).show()
    }

    /**
     * Control whether the internal FAB should be shown.
     * Useful when this fragment is embedded in another screen that has its own FABs.
     */
    fun hideInternalFab(hide: Boolean) {
        showInternalFab = !hide
        if (_binding != null) {
            binding.addProductFab.visibility = if (showInternalFab) View.VISIBLE else View.GONE
        }
    }
    
    /**
     * Set management mode - when true, clicking products shows edit dialog instead of navigation
     */
    fun setManagementMode(enabled: Boolean) {
        isManagementMode = enabled
    }
    
    private fun showEditProductDialog(product: Product) {
        val ctx = requireContext()
        val container = android.widget.LinearLayout(ctx).apply {
            orientation = android.widget.LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }
        
        val idInput = android.widget.EditText(ctx).apply {
            hint = "Product ID"
            setText(product.id)
            isEnabled = false // ID should not be editable
        }
        val nameInput = android.widget.EditText(ctx).apply {
            hint = "Product name"
            setText(product.name)
        }
        val priceInput = android.widget.EditText(ctx).apply {
            hint = "Price (RM, e.g. 3.50)"
            setText("%.2f".format(product.priceCents / 100.0))
        }
        val categoryLabel = android.widget.TextView(ctx).apply {
            text = "Category:"
            textSize = 14f
            setPadding(0, 16, 0, 8)
        }
        
        val categorySpinner = android.widget.Spinner(ctx)
        
        // Populate category spinner
        val categories = productViewModel.getCategories()
        val categoryOptions = mutableListOf("No Category")
        val categoryIds = mutableListOf("")
        
        categories.forEach { (id, name) ->
            categoryOptions.add("$name ($id)")
            categoryIds.add(id)
        }
        
        val adapter = android.widget.ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, categoryOptions)
        categorySpinner.adapter = adapter
        
        // Set current selection
        val currentIndex = categoryIds.indexOf(product.categoryId)
        if (currentIndex >= 0) {
            categorySpinner.setSelection(currentIndex)
        }
        val descriptionInput = android.widget.EditText(ctx).apply {
            hint = "Description (optional)"
            setText(product.description)
        }
        
        container.addView(idInput)
        container.addView(nameInput)
        container.addView(priceInput)
        container.addView(categoryLabel)
        container.addView(categorySpinner)
        container.addView(descriptionInput)

        androidx.appcompat.app.AlertDialog.Builder(ctx)
            .setTitle("Edit Product")
            .setView(container)
            .setPositiveButton("Save") { _, _ ->
                val name = nameInput.text.toString().trim()
                val priceText = priceInput.text.toString().trim()
                val selectedPosition = categorySpinner.selectedItemPosition
                val categoryId = if (selectedPosition > 0) categoryIds[selectedPosition] else ""
                val description = descriptionInput.text.toString().trim()
                
                if (name.isNotBlank() && priceText.isNotBlank()) {
                    val priceCents = try {
                        (priceText.replace(",", "").toDouble() * 100).toLong()
                    } catch (e: Exception) { product.priceCents }

                    val updatedProduct = product.copy(
                        name = name,
                        priceCents = priceCents,
                        categoryId = categoryId,
                        description = description
                    )
                    
                    productViewModel.updateProduct(updatedProduct)
                }
            }
            .setNegativeButton("Cancel", null)
            .setNeutralButton("Delete") { _, _ ->
                showDeleteConfirmation(product)
            }
            .show()
    }
    
    private fun showDeleteConfirmation(product: Product) {
        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Delete Product")
            .setMessage("Are you sure you want to delete '${product.name}'?")
            .setPositiveButton("Delete") { _, _ ->
                productViewModel.deleteProduct(product.id)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}