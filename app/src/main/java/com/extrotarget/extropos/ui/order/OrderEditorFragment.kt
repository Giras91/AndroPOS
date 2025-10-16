package com.extrotarget.extropos.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.core.os.bundleOf
import com.extrotarget.extropos.ui.order.OrderEditorAdapter
import com.extrotarget.extropos.ui.order.OrderEditorFragmentArgs
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import androidx.lifecycle.lifecycleScope
import com.extrotarget.extropos.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class OrderEditorFragment : Fragment() {

    private val orderViewModel: OrderViewModel by activityViewModels()

    private lateinit var adapter: OrderEditorAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_order_editor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Read nav-args (Safe Args generated Args class)
        val args = try {
            OrderEditorFragmentArgs.fromBundle(requireArguments())
        } catch (e: Exception) {
            null
        }

        val orderIdArg = args?.orderId ?: arguments?.getString("orderId")
        if (!orderIdArg.isNullOrBlank()) {
            // Load the order into the shared OrderViewModel
            orderViewModel.loadOrder(orderIdArg)
        }

        // Setup RecyclerView and adapter
        adapter = OrderEditorAdapter(onRemove = { item ->
            val orderId = orderViewModel.currentOrder.value?.id
            if (orderId != null) {
                orderViewModel.removeItemFromOrder(orderId, item.id)
                Snackbar.make(view, "Removed ${item.name}", Snackbar.LENGTH_SHORT).show()
            }
        })

        val recycler = view.findViewById<RecyclerView>(R.id.order_items_recycler)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        val orderIdView = view.findViewById<android.widget.TextView>(R.id.orderEditorOrderId)
        val totalView = view.findViewById<android.widget.TextView>(R.id.orderTotal)
        val checkoutBtn = view.findViewById<android.widget.Button>(R.id.orderCheckout)

        // Update UI when order changes
        lifecycleScope.launchWhenStarted {
            orderViewModel.currentOrder.collectLatest { order ->
                orderIdView.text = "Order ID: ${order?.id ?: "--"}"
                val items = order?.items ?: emptyList()
                adapter.submitList(items)
                val totalCents = order?.items?.sumOf { it.totalPriceCents } ?: 0L
                totalView.text = "Total: RM${"%.2f".format(totalCents / 100.0)}"
            }
        }

        checkoutBtn.setOnClickListener {
            val orderId = orderViewModel.currentOrder.value?.id
            if (orderId != null) {
                orderViewModel.updateOrderStatus(orderId, com.extrotarget.extropos.domain.model.OrderStatus.READY)
                Snackbar.make(view, "Order $orderId marked ready", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}
