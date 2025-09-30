package com.extrotarget.extropos.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.extrotarget.extropos.R
import com.extrotarget.extropos.domain.model.CartItem

class CartItemsAdapter(
    private val onQuantityChange: (CartItem, Int) -> Unit,
    private val onRemoveItem: (CartItem) -> Unit
) : ListAdapter<CartItem, CartItemsAdapter.CartItemViewHolder>(CartItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CartItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productNameTextView: TextView = itemView.findViewById(R.id.productNameTextView)
        private val quantityTextView: TextView = itemView.findViewById(R.id.quantityTextView)
        private val unitPriceTextView: TextView = itemView.findViewById(R.id.unitPriceTextView)
        private val totalPriceTextView: TextView = itemView.findViewById(R.id.totalPriceTextView)
        private val increaseButton: Button = itemView.findViewById(R.id.increaseButton)
        private val decreaseButton: Button = itemView.findViewById(R.id.decreaseButton)
        private val removeButton: Button = itemView.findViewById(R.id.removeButton)

        fun bind(item: CartItem) {
            productNameTextView.text = item.name
            quantityTextView.text = item.quantity.toString()
            unitPriceTextView.text = "RM ${String.format("%.2f", item.unitPriceCents / 100.0)}"
            totalPriceTextView.text = "RM ${String.format("%.2f", (item.unitPriceCents * item.quantity) / 100.0)}"

            increaseButton.setOnClickListener {
                onQuantityChange(item, item.quantity + 1)
            }

            decreaseButton.setOnClickListener {
                if (item.quantity > 1) {
                    onQuantityChange(item, item.quantity - 1)
                }
            }

            removeButton.setOnClickListener {
                onRemoveItem(item)
            }
        }
    }

    private class CartItemDiffCallback : DiffUtil.ItemCallback<CartItem>() {
        override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem.productId == newItem.productId
        }

        override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem == newItem
        }
    }
}