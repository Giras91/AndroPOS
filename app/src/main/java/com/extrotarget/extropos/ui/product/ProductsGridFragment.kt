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
    private val cartViewModel: CartViewModel by viewModels()

    private lateinit var productsAdapter: ProductsAdapter

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
        productsAdapter = ProductsAdapter { product ->
            // Try to navigate to product detail; if navigation isn't available, fallback to add to cart
            val navController = try {
                findNavController()
            } catch (e: Exception) {
                null
            }

            if (navController != null) {
                try {
                    val bundle = android.os.Bundle().apply { putString("productId", product.id) }
                    navController.navigate(com.extrotarget.extropos.R.id.productDetailFragment, bundle)
                } catch (e: Exception) {
                    // If navigation fails, fallback to adding to cart
                    addProductToCart(product)
                }
            } else {
                addProductToCart(product)
            }
        }

        // Compute a responsive column count based on screen width and desired tile width (approx 180dp)
        val displayMetrics = resources.displayMetrics
        val screenWidthPx = displayMetrics.widthPixels
        val density = displayMetrics.density
        val desiredTileDp = 180f
        val desiredTilePx = (desiredTileDp * density).toInt()
        val columns = (screenWidthPx / desiredTilePx).coerceAtLeast(1)

        binding.productsRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), columns)
            adapter = productsAdapter
            // Apply spacing in px (12dp)
            val spacingPx = (12 * density).toInt()
            addItemDecoration(GridSpacingItemDecoration(spacingPx))
        }
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
        if (product.stockQuantity <= 0) {
            // TODO: Show out of stock message
            return
        }

        cartViewModel.addItem(product.id, product.name, product.priceCents, 1)
        // TODO: Show added to cart feedback
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}