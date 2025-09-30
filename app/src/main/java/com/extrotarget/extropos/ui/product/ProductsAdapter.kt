package com.extrotarget.extropos.ui.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.extrotarget.extropos.databinding.ItemProductBinding
import com.extrotarget.extropos.domain.model.Product

class ProductsAdapter(
    private val onProductClick: (Product) -> Unit
) : ListAdapter<Product, ProductsAdapter.ProductViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProductViewHolder(
        private val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onProductClick(getItem(position))
                }
            }
        }

        fun bind(product: Product) {
            binding.apply {
                productNameTextView.text = product.name
                productDescriptionTextView.text = product.description
                productPriceTextView.text = "RM ${String.format("%.2f", product.priceCents / 100.0)}"
                
                // Handle availability
                if (product.isAvailable) {
                    availabilityTextView.text = "Available"
                    availabilityTextView.setTextColor(
                        binding.root.context.getColor(android.R.color.holo_green_dark)
                    )
                    root.alpha = 1.0f
                } else {
                    availabilityTextView.text = "Out of Stock"
                    availabilityTextView.setTextColor(
                        binding.root.context.getColor(android.R.color.holo_red_dark)
                    )
                    root.alpha = 0.6f
                }
            }
        }
    }

    private class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}