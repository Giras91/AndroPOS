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

@AndroidEntryPoint
class PosFragment : Fragment() {

    private val cartViewModel: CartViewModel by viewModels()
    private val ticketViewModel: TicketViewModel by viewModels()
    private val orderViewModel: OrderViewModel by activityViewModels()

    private lateinit var cartRecycler: RecyclerView
    private lateinit var subtotalView: TextView
    private lateinit var taxView: TextView
    private lateinit var totalView: TextView
    private lateinit var checkoutButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
}
