package com.extrotarget.extropos.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.TextView
import com.extrotarget.extropos.R
import com.extrotarget.extropos.domain.model.CartItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartFragment : Fragment() {

    private val cartViewModel: CartViewModel by viewModels()
    private lateinit var cartItemsAdapter: CartItemsAdapter
    
    private lateinit var cartItemsRecyclerView: RecyclerView
    private lateinit var subtotalTextView: TextView
    private lateinit var taxTextView: TextView
    private lateinit var totalTextView: TextView
    private lateinit var itemCountTextView: TextView
    private lateinit var payButton: Button
    private lateinit var clearButton: Button
    private lateinit var suspendButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        initializeViews(view)
        setupRecyclerView()
        setupButtons()
        observeViewModel()
    }
    
    private fun initializeViews(view: View) {
        cartItemsRecyclerView = view.findViewById(R.id.cartItemsRecyclerView)
        subtotalTextView = view.findViewById(R.id.subtotalTextView)
        taxTextView = view.findViewById(R.id.taxTextView)
        totalTextView = view.findViewById(R.id.totalTextView) 
        itemCountTextView = view.findViewById(R.id.itemCountTextView)
        payButton = view.findViewById(R.id.payButton)
        clearButton = view.findViewById(R.id.clearButton)
        suspendButton = view.findViewById(R.id.suspendButton)
    }

    private fun setupRecyclerView() {
        cartItemsAdapter = CartItemsAdapter(
            onQuantityChange = { item, newQuantity -> 
                cartViewModel.updateItemQuantity(item, newQuantity)
            },
            onRemoveItem = { item -> 
                cartViewModel.removeItem(item)
            }
        )

        cartItemsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cartItemsAdapter
        }
    }

    private fun setupButtons() {
        payButton.setOnClickListener {
            cartViewModel.completeTicket()
        }

        clearButton.setOnClickListener {
            cartViewModel.clearCart()
        }

        suspendButton.setOnClickListener {
            cartViewModel.suspendTicket()
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            cartViewModel.items.collect { items ->
                cartItemsAdapter.submitList(items)
                updateTotals()
            }
        }
    }

    private fun updateTotals() {
        subtotalTextView.text = cartViewModel.getFormattedSubtotal()
        taxTextView.text = cartViewModel.getFormattedTax()
        totalTextView.text = cartViewModel.getFormattedTotal()
        itemCountTextView.text = "${cartViewModel.itemCount.value} items"
    }
}