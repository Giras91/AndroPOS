package com.extrotarget.extropos.ui.settings.table

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.extrotarget.extropos.databinding.ItemTableSectionBinding
import com.extrotarget.extropos.domain.model.TableSection

class TableSectionsAdapter(
    private val onEditClick: (TableSection) -> Unit,
    private val onDeleteClick: (TableSection) -> Unit
) : ListAdapter<TableSection, TableSectionsAdapter.SectionViewHolder>(Diff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val binding = ItemTableSectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) = holder.bind(getItem(position))

    inner class SectionViewHolder(private val binding: ItemTableSectionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(section: TableSection) {
            binding.nameText.text = section.name
            binding.descriptionText.apply {
                text = section.description
                visibility = if (section.description.isNullOrBlank()) View.GONE else View.VISIBLE
            }
            binding.activeText.text = if (section.isActive) "Active" else "Inactive"

            // Color swatch
            val color = section.color?.let {
                runCatching { Color.parseColor(it) }.getOrNull() ?: Color.GREEN
            } ?: Color.GREEN
            binding.colorSwatch.setBackgroundColor(color)

            binding.editButton.setOnClickListener { onEditClick(section) }
            binding.deleteButton.setOnClickListener { onDeleteClick(section) }
            binding.root.setOnClickListener { onEditClick(section) }
        }
    }

    private class Diff : DiffUtil.ItemCallback<TableSection>() {
        override fun areItemsTheSame(oldItem: TableSection, newItem: TableSection) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: TableSection, newItem: TableSection) = oldItem == newItem
    }
}
