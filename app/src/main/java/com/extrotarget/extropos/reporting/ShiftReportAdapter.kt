package com.extrotarget.extropos.reporting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.extrotarget.extropos.R

class ShiftReportAdapter(private var items: List<ShiftReport>) : RecyclerView.Adapter<ShiftReportAdapter.VH>() {

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        val tvUser: TextView = view.findViewById(R.id.tvShiftReportUser)
        val tvRange: TextView = view.findViewById(R.id.tvShiftReportRange)
        val tvSales: TextView = view.findViewById(R.id.tvShiftReportSales)
        val rvProducts: androidx.recyclerview.widget.RecyclerView = view.findViewById(R.id.rvShiftProducts)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_shift_report, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val s = items[position]
        holder.tvUser.text = s.username
        val fmt = java.text.SimpleDateFormat("dd/MM HH:mm", java.util.Locale.getDefault())
        val endText = s.endedAt?.let { fmt.format(java.util.Date(it)) } ?: "(open)"
        holder.tvRange.text = "${fmt.format(java.util.Date(s.startedAt))} - $endText"
        val rm = s.salesTotalCents.toDouble() / 100.0
        holder.tvSales.text = "Sales: ${s.salesCount} (RM ${String.format("%.2f", rm)})"
        // setup nested recycler for product breakdown
        holder.rvProducts.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(holder.rvProducts.context)
        val prodAdapter = ProductSummaryAdapter(s.productBreakdown)
        holder.rvProducts.adapter = prodAdapter
    }

    override fun getItemCount(): Int = items.size

    fun update(newItems: List<ShiftReport>) {
        items = newItems
        notifyDataSetChanged()
    }
}
