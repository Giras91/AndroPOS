package com.extrotarget.extropos.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.extrotarget.extropos.databinding.ItemTableSelectionBinding
import com.extrotarget.extropos.domain.model.Table
import com.extrotarget.extropos.domain.model.TableStatus

class TableSelectionAdapter(
    private val onTableClick: (Table) -> Unit
) : ListAdapter<Table, TableSelectionAdapter.TableViewHolder>(TableDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val binding = ItemTableSelectionBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TableViewHolder(binding, onTableClick)
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TableViewHolder(
        private val binding: ItemTableSelectionBinding,
        private val onTableClick: (Table) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private var currentTable: Table? = null

        init {
            binding.root.setOnClickListener {
                currentTable?.let { onTableClick(it) }
            }
        }

        fun bind(table: Table) {
            currentTable = table
            binding.tableNumber.text = "T${table.number}"
            binding.tableCapacity.text = "${table.capacity} seats"

            // Set status text and color
            when (table.status) {
                TableStatus.AVAILABLE -> {
                    binding.tableStatus.text = "Available"
                    binding.tableStatus.setTextColor(binding.root.context.getColor(android.R.color.holo_green_dark))
                    binding.root.isEnabled = true
                    binding.root.alpha = 1.0f
                }
                TableStatus.OCCUPIED -> {
                    binding.tableStatus.text = "Occupied"
                    binding.tableStatus.setTextColor(binding.root.context.getColor(android.R.color.holo_red_dark))
                    binding.root.isEnabled = false
                    binding.root.alpha = 0.5f
                }
                TableStatus.RESERVED -> {
                    binding.tableStatus.text = "Reserved"
                    binding.tableStatus.setTextColor(binding.root.context.getColor(android.R.color.holo_orange_dark))
                    binding.root.isEnabled = false
                    binding.root.alpha = 0.5f
                }
                TableStatus.OUT_OF_ORDER -> {
                    binding.tableStatus.text = "Out of Order"
                    binding.tableStatus.setTextColor(binding.root.context.getColor(android.R.color.darker_gray))
                    binding.root.isEnabled = false
                    binding.root.alpha = 0.3f
                }
            }
        }
    }

    private class TableDiffCallback : DiffUtil.ItemCallback<Table>() {
        override fun areItemsTheSame(oldItem: Table, newItem: Table): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Table, newItem: Table): Boolean {
            return oldItem == newItem
        }
    }
}