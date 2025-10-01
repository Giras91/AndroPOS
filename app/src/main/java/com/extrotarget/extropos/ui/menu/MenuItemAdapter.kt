package com.extrotarget.extropos.ui.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.extrotarget.extropos.databinding.ItemMenuItemBinding
import com.extrotarget.extropos.domain.model.MenuItem

class MenuItemAdapter(
    private val onMenuItemClick: (MenuItem) -> Unit
) : ListAdapter<MenuItem, MenuItemAdapter.MenuItemViewHolder>(MenuItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        val binding = ItemMenuItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MenuItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MenuItemViewHolder(
        private val binding: ItemMenuItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onMenuItemClick(getItem(position))
                }
            }
        }

        fun bind(menuItem: MenuItem) {
            binding.itemNameTextView.text = menuItem.name
            binding.itemDescriptionTextView.text = menuItem.description
            binding.itemPriceTextView.text = formatPrice(menuItem.priceCents)

            // Show preparation time if available
            if (menuItem.preparationTimeMinutes > 0) {
                binding.preparationTimeTextView.text = "${menuItem.preparationTimeMinutes} min"
                binding.preparationTimeTextView.visibility = android.view.View.VISIBLE
            } else {
                binding.preparationTimeTextView.visibility = android.view.View.GONE
            }

            // Show availability status
            binding.availabilityTextView.visibility =
                if (menuItem.isAvailable) android.view.View.GONE else android.view.View.VISIBLE

            // Show allergens if available
            menuItem.allergens?.let { allergens ->
                if (allergens.isNotEmpty()) {
                    binding.allergensTextView.text = "Allergens: ${allergens.joinToString(", ")}"
                    binding.allergensTextView.visibility = android.view.View.VISIBLE
                } else {
                    binding.allergensTextView.visibility = android.view.View.GONE
                }
            } ?: run {
                binding.allergensTextView.visibility = android.view.View.GONE
            }
        }

        private fun formatPrice(priceCents: Long): String {
            val price = priceCents / 100.0
            return "RM %.2f".format(price)
        }
    }

    private class MenuItemDiffCallback : DiffUtil.ItemCallback<MenuItem>() {
        override fun areItemsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
            return oldItem == newItem
        }
    }
}