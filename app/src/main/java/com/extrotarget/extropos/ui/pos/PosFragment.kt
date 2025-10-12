package com.extrotarget.extropos.ui.pos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.extrotarget.extropos.R
import com.extrotarget.extropos.ui.cart.CartViewModel
import com.extrotarget.extropos.ui.cart.TicketViewModel
import com.extrotarget.extropos.ui.order.OrderViewModel
import com.extrotarget.extropos.ui.product.ProductsAdapter
import com.extrotarget.extropos.ui.product.ProductsGridFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import android.util.Log
import androidx.core.widget.addTextChangedListener

@AndroidEntryPoint
class PosFragment : Fragment() {

    private val cartViewModel: CartViewModel by activityViewModels()
    private val ticketViewModel: TicketViewModel by viewModels()
    private val orderViewModel: OrderViewModel by activityViewModels()
    private val productViewModel: com.extrotarget.extropos.ui.product.ProductViewModel by activityViewModels()

    private lateinit var cartRecycler: RecyclerView
    private lateinit var subtotalView: TextView
    private lateinit var taxView: TextView
    private lateinit var totalView: TextView
    private lateinit var itemsCountView: TextView
    private lateinit var checkoutButton: Button
    private var productsGridFragment: com.extrotarget.extropos.ui.product.ProductsGridFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Debug log to prove PosFragment was instantiated and key views exist
        try {
            val hasProductsContainer = view.findViewById<View?>(R.id.pos_products_container) != null
            val hasCartRecycler = view.findViewById<View?>(R.id.pos_cart_recycler) != null
            Log.i("DashboardDebug", "PosFragment created - productsContainer=${hasProductsContainer}, cartRecycler=${hasCartRecycler}")
        } catch (e: Exception) {
            Log.i("DashboardDebug", "PosFragment created - error checking views: ${e.message}")
        }

        cartRecycler = view.findViewById(R.id.pos_cart_recycler)
        subtotalView = view.findViewById(R.id.pos_subtotal)
        taxView = view.findViewById(R.id.pos_tax)
        totalView = view.findViewById(R.id.pos_total)
        itemsCountView = view.findViewById(R.id.pos_items_count)
        checkoutButton = view.findViewById(R.id.pos_checkout_button)

        // Cart list
        cartRecycler.layoutManager = LinearLayoutManager(requireContext())
        val cartAdapter = com.extrotarget.extropos.ui.cart.CartItemsAdapter(
            onQuantityChange = { item, newQty -> cartViewModel.updateItemQuantity(item, newQty) },
            onRemoveItem = { item -> cartViewModel.removeItem(item) }
        )
        cartRecycler.adapter = cartAdapter

        // Observe cart and ticket totals
        lifecycleScope.launch {
            cartViewModel.items.collectLatest { items ->
                Log.d("PosFragment", "Cart items updated: ${items.size} items")
                for (item in items) {
                    Log.d("PosFragment", "  - ${item.name} x${item.quantity} @ RM${item.unitPriceCents/100.0}")
                }
                
                cartAdapter.submitList(items)
                subtotalView.text = cartViewModel.getFormattedSubtotal()
                taxView.text = cartViewModel.getFormattedTax()
                totalView.text = cartViewModel.getFormattedTotal()
                
                // Update items count
                val itemCount = items.sumOf { it.quantity }
                itemsCountView.text = if (itemCount == 1) "1 item" else "$itemCount items"
                
                Log.d("PosFragment", "UI updated - total items: $itemCount")
            }
        }

        // Load products via ProductsGridFragment helper if needed
        if (childFragmentManager.findFragmentById(R.id.pos_products_container) == null) {
            Log.d("PosFragment", "Creating new ProductsGridFragment")
            val fragment = ProductsGridFragment()
            childFragmentManager.beginTransaction()
                .replace(R.id.pos_products_container, fragment, "products_grid")
                .commitNowAllowingStateLoss() // Use commitNow for immediate execution
        }

        // Get reference to the child fragment and configure it
        view.post {
            productsGridFragment = childFragmentManager.findFragmentById(R.id.pos_products_container) as? com.extrotarget.extropos.ui.product.ProductsGridFragment
            Log.d("PosFragment", "ProductsGridFragment reference: ${productsGridFragment != null}")

            // Configure the fragment for POS mode (selling, not management)
            productsGridFragment?.setManagementMode(false)
            productsGridFragment?.hideInternalSearch(true)
            productsGridFragment?.hideInternalFab(true)
            
            Log.d("PosFragment", "ProductsGridFragment configured for POS mode")
        }

        // If activity requested a debug search query (from DebugLauncherActivity), run it
        try {
            val activityIntent = requireActivity().intent
            val debugQuery = activityIntent?.getStringExtra("debug_pos_search_query")
            if (!debugQuery.isNullOrBlank()) {
                // Ensure the child fragment is available and then run the debug search
                view.post {
                    productsGridFragment = childFragmentManager.findFragmentById(R.id.pos_products_container) as? com.extrotarget.extropos.ui.product.ProductsGridFragment
                    productsGridFragment?.debugRunSearch(debugQuery)
                }
            }
        } catch (_: Exception) {
            // ignore debug wiring failures
        }

        // Wire search input (if present in layout)
        try {
            val searchView = view.findViewById<android.widget.EditText?>(R.id.pos_search)
            searchView?.addTextChangedListener { editable ->
                val q = editable?.toString() ?: ""
                ensureProductsFragmentAndSearch(q)
            }
        } catch (_: Exception) {
            // ignore if TextInputEditText type differs; search will still work if user focuses on child fragment
        }

        // Category chips will be setup via observation and initial call later

        checkoutButton.setOnClickListener {
            // Create an order (use current table id "walkin" for now)
            val tableId = "walkin"
            orderViewModel.createNewOrder(tableId)
        }

        // Observe order creation result and update UI
        lifecycleScope.launch {
            orderViewModel.currentOrder.collectLatest { order ->
                // show minimal feedback (title in subtotal)
                if (order != null) {
                    subtotalView.text = "Order: ${order.id}"
                }
            }
        }

        // Load data first to ensure categories are available
        Log.d("PosFragment", "Loading products from database")
        productViewModel.loadProducts()
        
        // Observe products for debugging
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                productViewModel.products.collectLatest { products ->
                    Log.d("PosFragment", "Products changed: ${products.size} products available")
                }
            }
        }
        
        // Observe category changes and refresh category chips
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                productViewModel.categories.collectLatest { categories ->
                    Log.d("PosFragment", "Categories changed: ${categories.size} categories")
                    setupCategoryChips(view)
                }
            }
        }

        // Initial setup after a short delay to ensure fragment is ready
        view.postDelayed({
            Log.d("PosFragment", "Initial category setup")
            setupCategoryChips(view)
            // Double-check that internal search is hidden
            ensureInternalSearchHidden()
        }, 200)
        
        // Also try hiding after a longer delay to catch late fragment initialization
        view.postDelayed({
            ensureInternalSearchHidden()
        }, 500)
    }

    // Debug helper: auto add product by id (used by DebugLauncherActivity via MainActivity)
    fun autoAddProduct(productId: String) {
        try {
            // Ensure child fragment reference is current
            productsGridFragment = childFragmentManager.findFragmentById(R.id.pos_products_container) as? com.extrotarget.extropos.ui.product.ProductsGridFragment
            val product = productsGridFragment?.getProductById(productId)
            if (product != null) {
                // Use shared CartViewModel to add item
                cartViewModel.addItem(product.id, product.name, product.priceCents, 1)
                Log.i("DashboardDebug", "AutoAddProduct: id=${product.id} name=${product.name}")
            } else {
                Log.i("DashboardDebug", "AutoAddProduct: product not found id=$productId")
            }
        } catch (e: Exception) {
            Log.i("DashboardDebug", "AutoAddProduct failed: ${e.message}")
        }
    }

    private fun setupCategoryChips(view: View) {
        try {
            val categoriesContainer = view.findViewById<android.widget.LinearLayout?>(R.id.pos_categories)
            if (categoriesContainer != null) {
                // Get categories directly from ProductViewModel (more reliable)
                val categories = productViewModel.getCategories()
                
                Log.d("PosFragment", "Setting up category chips: ${categories.size} categories found")
                for (cat in categories) {
                    Log.d("PosFragment", "Category: ${cat.first} - ${cat.second}")
                }
                
                // Add buttons for categories (including an "All" button)
                categoriesContainer.removeAllViews()
                val allButton = com.google.android.material.button.MaterialButton(requireContext()).apply {
                    text = "All"
                    textSize = 12f
                    layoutParams = android.widget.LinearLayout.LayoutParams(
                        android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
                        48 // Fixed height in dp converted to pixels
                    ).apply {
                        setMargins(4, 4, 4, 4)
                    }
                    setOnClickListener {
                        ensureProductsFragmentAndFilter(null)
                        updateButtonSelection(categoriesContainer, this)
                    }
                }
                categoriesContainer.addView(allButton)
                
                // Set initial selection
                updateButtonSelection(categoriesContainer, allButton)

                for ((id, name) in categories) {
                    val button = com.google.android.material.button.MaterialButton(requireContext()).apply {
                        text = name
                        textSize = 12f
                        layoutParams = android.widget.LinearLayout.LayoutParams(
                            android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
                            48 // Fixed height in dp converted to pixels
                        ).apply {
                            setMargins(4, 4, 4, 4)
                        }
                        setOnClickListener {
                            ensureProductsFragmentAndFilter(id)
                            updateButtonSelection(categoriesContainer, this)
                        }
                    }
                    categoriesContainer.addView(button)
                }
            }
        } catch (e: Exception) {
            Log.d("PosFragment", "Failed to setup category chips: ${e.message}")
        }
    }

    private fun ensureProductsFragmentAndFilter(categoryId: String?) {
        try {
            // Ensure we have a reference to the child fragment
            productsGridFragment = productsGridFragment ?: childFragmentManager.findFragmentById(R.id.pos_products_container) as? com.extrotarget.extropos.ui.product.ProductsGridFragment
            
            if (productsGridFragment != null) {
                productsGridFragment?.filterByCategory(categoryId)
                // Also ensure the fragment is properly configured
                productsGridFragment?.hideInternalSearch(true)
                productsGridFragment?.hideInternalFab(true)
                Log.d("PosFragment", "Filtered products by category: $categoryId")
            } else {
                Log.w("PosFragment", "ProductsGridFragment not found, will retry after delay")
                // Retry after a short delay if fragment isn't ready
                view?.postDelayed({
                    productsGridFragment = childFragmentManager.findFragmentById(R.id.pos_products_container) as? com.extrotarget.extropos.ui.product.ProductsGridFragment
                    productsGridFragment?.filterByCategory(categoryId)
                    productsGridFragment?.hideInternalSearch(true)
                    productsGridFragment?.hideInternalFab(true)
                }, 100)
            }
        } catch (e: Exception) {
            Log.e("PosFragment", "Failed to filter products by category", e)
        }
    }

    private fun ensureProductsFragmentAndSearch(query: String) {
        try {
            // Ensure we have a reference to the child fragment
            productsGridFragment = productsGridFragment ?: childFragmentManager.findFragmentById(R.id.pos_products_container) as? com.extrotarget.extropos.ui.product.ProductsGridFragment
            
            if (productsGridFragment != null) {
                productsGridFragment?.setSearchQuery(query)
                // Also ensure the fragment is properly configured
                productsGridFragment?.hideInternalSearch(true)
                productsGridFragment?.hideInternalFab(true)
                Log.d("PosFragment", "Set search query: $query")
            } else {
                Log.w("PosFragment", "ProductsGridFragment not found, will retry after delay")
                // Retry after a short delay if fragment isn't ready
                view?.postDelayed({
                    productsGridFragment = childFragmentManager.findFragmentById(R.id.pos_products_container) as? com.extrotarget.extropos.ui.product.ProductsGridFragment
                    productsGridFragment?.setSearchQuery(query)
                    productsGridFragment?.hideInternalSearch(true)
                    productsGridFragment?.hideInternalFab(true)
                }, 100)
            }
        } catch (e: Exception) {
            Log.e("PosFragment", "Failed to set search query", e)
        }
    }

    private fun ensureInternalSearchHidden() {
        try {
            productsGridFragment = productsGridFragment ?: childFragmentManager.findFragmentById(R.id.pos_products_container) as? com.extrotarget.extropos.ui.product.ProductsGridFragment
            productsGridFragment?.let { fragment ->
                fragment.hideInternalSearch(true)
                fragment.hideInternalFab(true)
                Log.d("PosFragment", "Ensured internal search is hidden")
            }
        } catch (e: Exception) {
            Log.e("PosFragment", "Failed to hide internal search", e)
        }
    }

    private fun updateButtonSelection(container: android.widget.LinearLayout, selectedButton: com.google.android.material.button.MaterialButton) {
        // Clear all other button selections (reset to normal style)
        for (i in 0 until container.childCount) {
            val child = container.getChildAt(i)
            if (child is com.google.android.material.button.MaterialButton) {
                if (child == selectedButton) {
                    // Selected button - use filled style
                    child.setBackgroundColor(androidx.core.content.ContextCompat.getColor(requireContext(), com.google.android.material.R.color.design_default_color_primary))
                    child.setTextColor(androidx.core.content.ContextCompat.getColor(requireContext(), android.R.color.white))
                } else {
                    // Unselected buttons - use outlined style
                    child.setBackgroundColor(androidx.core.content.ContextCompat.getColor(requireContext(), android.R.color.transparent))
                    child.setTextColor(androidx.core.content.ContextCompat.getColor(requireContext(), com.google.android.material.R.color.design_default_color_primary))
                }
            }
        }
    }
}
