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

    private lateinit var cartRecycler: RecyclerView
    private lateinit var subtotalView: TextView
    private lateinit var taxView: TextView
    private lateinit var totalView: TextView
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
                cartAdapter.submitList(items)
                subtotalView.text = cartViewModel.getFormattedSubtotal()
                taxView.text = cartViewModel.getFormattedTax()
                totalView.text = cartViewModel.getFormattedTotal()
            }
        }

        // Load products via ProductsGridFragment helper if needed
        childFragmentManager.beginTransaction()
            .replace(R.id.pos_products_container, ProductsGridFragment())
            .commitAllowingStateLoss()

        // Attempt to find the child ProductsGridFragment (it may be attached synchronously)
        productsGridFragment = childFragmentManager.findFragmentById(R.id.pos_products_container) as? com.extrotarget.extropos.ui.product.ProductsGridFragment

    // Hide the child fragment's internal search since parent provides one
    productsGridFragment?.hideInternalSearch(true)

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
                // If child fragment is available, forward; otherwise, call when available
                productsGridFragment?.setSearchQuery(q) ?: run {
                    // Postpone by locating child later
                    view.post {
                        productsGridFragment = childFragmentManager.findFragmentById(R.id.pos_products_container) as? com.extrotarget.extropos.ui.product.ProductsGridFragment
                        productsGridFragment?.setSearchQuery(q)
                    }
                }
            }
        } catch (_: Exception) {
            // ignore if TextInputEditText type differs; search will still work if user focuses on child fragment
        }

        // Populate category buttons
        try {
            val categoriesContainer = view.findViewById<android.widget.LinearLayout?>(R.id.pos_categories)
            if (categoriesContainer != null) {
                // Ensure we have a reference to the child fragment and load categories
                productsGridFragment = productsGridFragment ?: childFragmentManager.findFragmentById(R.id.pos_products_container) as? com.extrotarget.extropos.ui.product.ProductsGridFragment
                val categories = productsGridFragment?.getAvailableCategories() ?: emptyList()
                // Add chips for categories (including an "All" chip)
                categoriesContainer.removeAllViews()
                val allChip = com.google.android.material.chip.Chip(requireContext())
                allChip.text = "All"
                allChip.isCheckable = true
                allChip.isChecked = true
                allChip.setOnClickListener {
                    productsGridFragment?.filterByCategory(null)
                    // scroll to top of products
                    view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.pos_cart_recycler)?.smoothScrollToPosition(0)
                }
                categoriesContainer.addView(allChip)

                for ((id, name) in categories) {
                    val chip = com.google.android.material.chip.Chip(requireContext())
                    chip.text = name
                    chip.isCheckable = true
                    chip.setOnClickListener {
                        productsGridFragment?.filterByCategory(id)
                        // scroll to top of products
                        view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.pos_cart_recycler)?.smoothScrollToPosition(0)
                    }
                    val lp = android.view.ViewGroup.MarginLayoutParams(
                        android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                        android.view.ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    lp.setMargins(8, 4, 8, 4)
                    chip.layoutParams = lp
                    categoriesContainer.addView(chip)
                }
            }
        } catch (_: Exception) {
            // ignore UI wiring failures quietly in debug
        }

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
}
