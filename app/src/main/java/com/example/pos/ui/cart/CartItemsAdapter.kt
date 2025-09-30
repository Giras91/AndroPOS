package com.example.pos.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pos.databinding.ItemCartBinding
import com.example.pos.domain.model.TicketItem

class CartItemsAdapter(
    private val onQuantityChanged: (TicketItem, Int) -> Unit,
    private val onItemRemoved: (TicketItem) -> Unit
) : ListAdapter<TicketItem, CartItemsAdapter.CartItemViewHolder>(CartItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val binding = ItemCartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CartItemViewHolder(binding, onQuantityChanged, onItemRemoved)
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CartItemViewHolder(
        private val binding: ItemCartBinding,
        private val onQuantityChanged: (TicketItem, Int) -> Unit,
        private val onItemRemoved: (TicketItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TicketItem) {
            binding.productNameTextView.text = item.productName
            binding.quantityTextView.text = item.quantity.toString()
            binding.unitPriceTextView.text = item.getFormattedUnitPrice()
            binding.totalPriceTextView.text = item.getFormattedTotal()

            binding.increaseButton.setOnClickListener {
                val newQty = item.quantity + 1
                onQuantityChanged(item, newQty)
            }

            binding.decreaseButton.setOnClickListener {
                if (item.quantity > 1) {
                    val newQty = item.quantity - 1
                    onQuantityChanged(item, newQty)
                }
            }

            binding.removeButton.setOnClickListener {
                onItemRemoved(item)
            }
        }
    }

    class CartItemDiffCallback : DiffUtil.ItemCallback<TicketItem>() {
        override fun areItemsTheSame(oldItem: TicketItem, newItem: TicketItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TicketItem, newItem: TicketItem): Boolean {
            return oldItem == newItem
        }
    }
}