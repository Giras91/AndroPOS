package com.extrotarget.extropos.ui.settings.table

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.extrotarget.extropos.R
import com.extrotarget.extropos.domain.model.Table
import com.extrotarget.extropos.databinding.ItemTableConfigurationBinding

class TableConfigurationAdapter(
    private val onEditClick: (Table) -> Unit,
    private val onDeleteClick: (Table) -> Unit
) : ListAdapter<Table, TableConfigurationAdapter.TableViewHolder>(TableDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val binding = ItemTableConfigurationBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TableViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TableViewHolder(
        private val binding: ItemTableConfigurationBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(table: Table) {
            binding.apply {
                tableNumberText.text = table.number.toString()
                capacityText.text = "Capacity: ${table.capacity}"
                sectionText.text = table.section ?: "No Section"
                statusText.text = table.status.name.replace("_", " ").lowercase().replaceFirstChar { it.uppercase() }

                // Status indicator
                statusIndicator.setBackgroundResource(
                    when (table.status) {
                        com.extrotarget.extropos.domain.model.TableStatus.AVAILABLE -> R.drawable.status_available
                        com.extrotarget.extropos.domain.model.TableStatus.OCCUPIED -> R.drawable.status_occupied
                        com.extrotarget.extropos.domain.model.TableStatus.RESERVED -> R.drawable.status_reserved
                        else -> R.drawable.status_unknown
                    }
                )

                // Amenities indicators
                smokingIcon.visibility = if (table.isSmokingAllowed) android.view.View.VISIBLE else android.view.View.GONE
                accessibleIcon.visibility = if (table.isAccessible) android.view.View.VISIBLE else android.view.View.GONE
                powerIcon.visibility = if (table.hasPowerOutlet) android.view.View.VISIBLE else android.view.View.GONE

                // Click listeners
                editButton.setOnClickListener { onEditClick(table) }
                deleteButton.setOnClickListener { onDeleteClick(table) }

                root.setOnClickListener { onEditClick(table) }
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