package com.example.pos.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pos.databinding.FragmentCartBinding
import com.example.pos.domain.model.TicketItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private val cartViewModel: CartViewModel by viewModels()
    private val ticketViewModel: TicketViewModel by viewModels()

    private lateinit var cartItemsAdapter: CartItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupButtons()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        cartItemsAdapter = CartItemsAdapter(
            onQuantityChanged = { item, newQty ->
                ticketViewModel.updateItemQuantity(item, newQty)
            },
            onItemRemoved = { item ->
                ticketViewModel.removeItem(item)
            }
        )

        binding.cartItemsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cartItemsAdapter
        }
    }

    private fun setupButtons() {
        binding.payButton.setOnClickListener {
            // TODO: Navigate to payment screen
        }

        binding.clearButton.setOnClickListener {
            ticketViewModel.clearCurrentTicket()
        }

        binding.suspendButton.setOnClickListener {
            ticketViewModel.suspendCurrentTicket()
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            ticketViewModel.currentTicket.collect { ticket ->
                ticket?.let {
                    val items = it.items
                    cartItemsAdapter.submitList(items)
                    updateTotals(it)
                } ?: run {
                    cartItemsAdapter.submitList(emptyList())
                    updateTotals(null)
                }
            }
        }
    }

    private fun updateTotals(ticket: com.example.pos.domain.model.Ticket?) {
        if (ticket == null) {
            binding.subtotalTextView.text = "RM 0.00"
            binding.taxTextView.text = "RM 0.00"
            binding.totalTextView.text = "RM 0.00"
            binding.itemCountTextView.text = "0 items"
        } else {
            binding.subtotalTextView.text = ticket.getFormattedSubtotal()
            binding.taxTextView.text = ticket.getFormattedTax()
            binding.totalTextView.text = ticket.getFormattedTotal()
            binding.itemCountTextView.text = "${ticket.items.sumOf { it.quantity }} items"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}