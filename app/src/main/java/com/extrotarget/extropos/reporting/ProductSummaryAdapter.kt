package com.extrotarget.extropos.reporting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.extrotarget.extropos.R
import java.util.Locale

class ProductSummaryAdapter(private var items: List<ProductSummary>) : RecyclerView.Adapter<ProductSummaryAdapter.VH>() {

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvProductName)
        val tvQty: TextView = view.findViewById(R.id.tvProductQty)
        val tvRevenue: TextView = view.findViewById(R.id.tvProductRevenue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_product_summary, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val it = items[position]
        holder.tvName.text = it.productName
        holder.tvQty.text = it.quantity.toString()
        holder.tvRevenue.text = formatCentsToRM(it.revenueCents)
    }

    override fun getItemCount(): Int = items.size

    fun update(newItems: List<ProductSummary>) {
        items = newItems
        notifyDataSetChanged()
    }

    private fun formatCentsToRM(cents: Long): String {
        val rm = cents.toDouble() / 100.0
        return String.format(Locale.getDefault(), "RM %.2f", rm)
    }
}
