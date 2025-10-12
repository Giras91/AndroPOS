package com.extrotarget.extropos.ui.settings.payment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.extrotarget.extropos.R
import com.extrotarget.extropos.data.model.TaxRate

class TaxRatesAdapter(
    private val onToggle: (TaxRate, Boolean) -> Unit,
    private val onSetDefault: (TaxRate) -> Unit
) : ListAdapter<TaxRate, TaxRatesAdapter.VH>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<TaxRate>() {
            override fun areItemsTheSame(oldItem: TaxRate, newItem: TaxRate): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: TaxRate, newItem: TaxRate): Boolean = oldItem == newItem
        }
    }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.taxRateTitle)
        val percent: TextView = itemView.findViewById(R.id.taxRatePercent)
        val toggle: Switch = itemView.findViewById(R.id.taxRateSwitch)
        val radio: RadioButton = itemView.findViewById(R.id.taxRateDefaultRadio)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tax_rate, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val rate = getItem(position)
    holder.title.text = rate.name
    holder.percent.text = "${rate.rate}%"
        holder.toggle.isChecked = rate.isEnabled
        holder.radio.isChecked = rate.isDefault

        holder.toggle.setOnCheckedChangeListener { _, isChecked -> onToggle(rate, isChecked) }
        holder.radio.setOnClickListener { onSetDefault(rate) }
    }
}
