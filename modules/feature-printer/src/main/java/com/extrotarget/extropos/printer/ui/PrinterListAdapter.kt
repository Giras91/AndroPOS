package com.extrotarget.extropos.printer.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.extrotarget.extropos.printer.domain.model.DetectedPrinter

class PrinterListAdapter(
    private val onConnect: (DetectedPrinter) -> Unit
) : ListAdapter<DetectedPrinter, PrinterListAdapter.VH>(DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_2, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        holder.title.text = item.name
        holder.subtitle.text = "${item.connectionType} • ${item.address ?: "—"}"
        holder.itemView.setOnClickListener { onConnect(item) }
    }

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(android.R.id.text1)
        val subtitle: TextView = view.findViewById(android.R.id.text2)
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<DetectedPrinter>() {
            override fun areItemsTheSame(oldItem: DetectedPrinter, newItem: DetectedPrinter): Boolean = oldItem.address == newItem.address
            override fun areContentsTheSame(oldItem: DetectedPrinter, newItem: DetectedPrinter): Boolean = oldItem == newItem
        }
    }
}
