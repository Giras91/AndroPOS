package com.extrotarget.extropos.ui.order

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.extrotarget.extropos.R
import com.extrotarget.extropos.domain.model.OrderItem

class OrderEditorAdapter(
    private val onRemove: (OrderItem) -> Unit
) : ListAdapter<OrderItem, OrderEditorAdapter.ViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_order_editor_line, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.removeButton.setOnClickListener { onRemove(item) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameView: TextView = itemView.findViewById(R.id.itemName)
        private val qtyView: TextView = itemView.findViewById(R.id.itemQty)
        private val priceView: TextView = itemView.findViewById(R.id.itemPrice)
        val removeButton: ImageButton = itemView.findViewById(R.id.removeButton)

        fun bind(item: OrderItem) {
            nameView.text = item.name
            qtyView.text = "x${item.quantity}"
            val price = item.totalPriceCents / 100.0
            priceView.text = String.format("RM%.2f", price)
        }
    }

    object Diff : DiffUtil.ItemCallback<OrderItem>() {
        override fun areItemsTheSame(oldItem: OrderItem, newItem: OrderItem): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: OrderItem, newItem: OrderItem): Boolean = oldItem == newItem
    }
}
