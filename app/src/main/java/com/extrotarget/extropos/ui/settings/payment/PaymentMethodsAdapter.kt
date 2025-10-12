package com.extrotarget.extropos.ui.settings.payment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.extrotarget.extropos.R
import com.extrotarget.extropos.data.model.PaymentMethod

class PaymentMethodsAdapter(
    private val onToggle: (PaymentMethod, Boolean) -> Unit,
    private val onEdit: (PaymentMethod) -> Unit,
    private val onDelete: (PaymentMethod) -> Unit
) : ListAdapter<PaymentMethod, PaymentMethodsAdapter.VH>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<PaymentMethod>() {
            override fun areItemsTheSame(oldItem: PaymentMethod, newItem: PaymentMethod): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: PaymentMethod, newItem: PaymentMethod): Boolean = oldItem == newItem
        }
    }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: ImageView = itemView.findViewById(R.id.paymentMethodIcon)
        val title: TextView = itemView.findViewById(R.id.paymentMethodTitle)
        val subtitle: TextView = itemView.findViewById(R.id.paymentMethodSubtitle)
        val toggle: Switch = itemView.findViewById(R.id.paymentMethodSwitch)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_payment_method, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val method = getItem(position)
    holder.title.text = method.displayName
    // show a short detail (processing fee) in subtitle
    val feeText = if (method.processingFeePercentage > 0) "Fee ${method.processingFeePercentage}%" else ""
    holder.subtitle.text = feeText
        holder.toggle.isChecked = method.isEnabled
        // TODO: set icon resource if available
        holder.toggle.setOnCheckedChangeListener { _, isChecked ->
            onToggle(method, isChecked)
        }
        holder.itemView.setOnClickListener { _ ->
            onEdit(method)
        }

        // Long press to delete (confirm in fragment)
        holder.itemView.setOnLongClickListener {
            onDelete(method)
            true
        }
    }
}
