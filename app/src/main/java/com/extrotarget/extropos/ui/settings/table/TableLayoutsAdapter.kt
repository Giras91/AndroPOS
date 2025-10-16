package com.extrotarget.extropos.ui.settings.table

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.extrotarget.extropos.databinding.ItemTableLayoutBinding
import com.extrotarget.extropos.domain.model.TableLayout

class TableLayoutsAdapter(
    private var items: List<TableLayout>,
    private val onEdit: (TableLayout) -> Unit,
    private val onDelete: (TableLayout) -> Unit,
    private val onSetDefault: (TableLayout) -> Unit
) : RecyclerView.Adapter<TableLayoutsAdapter.VH>() {

    fun submit(list: List<TableLayout>) {
        items = list
        notifyDataSetChanged()
    }

    inner class VH(val binding: ItemTableLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TableLayout) {
            binding.name.text = item.name
            binding.description.text = item.description ?: ""
            binding.defaultBadge.visibility = if (item.isDefault) View.VISIBLE else View.GONE

            binding.btnEdit.setOnClickListener { onEdit(item) }
            binding.btnDelete.setOnClickListener { onDelete(item) }
            binding.btnSetDefault.setOnClickListener { onSetDefault(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTableLayoutBinding.inflate(inflater, parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
