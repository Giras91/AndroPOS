package com.extrotarget.extropos.reporting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.extrotarget.extropos.R
import com.extrotarget.extropos.data.local.entity.ShiftEntity
import java.util.concurrent.TimeUnit

class ShiftAdapter(private var items: List<ShiftEntity>) : RecyclerView.Adapter<ShiftAdapter.VH>() {

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        val tvUser: TextView = view.findViewById(R.id.tvShiftUser)
        val tvStarted: TextView = view.findViewById(R.id.tvShiftStarted)
        val tvEnded: TextView = view.findViewById(R.id.tvShiftEnded)
        val tvDuration: TextView = view.findViewById(R.id.tvShiftDuration)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_shift, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val s = items[position]
        holder.tvUser.text = s.username
        val fmt = java.text.SimpleDateFormat("dd/MM/yyyy HH:mm", java.util.Locale.getDefault())
        holder.tvStarted.text = fmt.format(java.util.Date(s.startedAt))
        holder.tvEnded.text = s.endedAt?.let { fmt.format(java.util.Date(it)) } ?: "(open)"
        holder.tvDuration.text = calculateDuration(s.startedAt, s.endedAt)
    }

    override fun getItemCount(): Int = items.size

    fun update(newItems: List<ShiftEntity>) {
        items = newItems
        notifyDataSetChanged()
    }

    private fun calculateDuration(start: Long, end: Long?): String {
        val endTs = end ?: System.currentTimeMillis()
        val diff = endTs - start
        val hrs = TimeUnit.MILLISECONDS.toHours(diff)
        val mins = TimeUnit.MILLISECONDS.toMinutes(diff) - TimeUnit.HOURS.toMinutes(hrs)
        return String.format("%dh %dm", hrs, mins)
    }
}
