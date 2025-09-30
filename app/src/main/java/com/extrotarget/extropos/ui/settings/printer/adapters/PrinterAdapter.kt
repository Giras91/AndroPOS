package com.extrotarget.extropos.ui.settings.printer.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.extrotarget.extropos.databinding.ItemPrinterBinding
import com.extrotarget.extropos.ui.settings.printer.Printer

class PrinterAdapter(
    private val onPrinterClick: (Printer) -> Unit,
    private val onTestPrintClick: (Printer) -> Unit,
    private val onDeleteClick: (Printer) -> Unit
) : ListAdapter<Printer, PrinterAdapter.PrinterViewHolder>(PrinterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrinterViewHolder {
        val binding = ItemPrinterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PrinterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PrinterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PrinterViewHolder(private val binding: ItemPrinterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(printer: Printer) {
            binding.printerNameTextView.text = printer.name
            binding.printerTypeTextView.text = when (printer.type) {
                com.extrotarget.extropos.ui.settings.printer.PrinterType.RECEIPT -> "Receipt Printer"
                com.extrotarget.extropos.ui.settings.printer.PrinterType.KITCHEN -> "Kitchen Printer"
            }
            binding.connectionTypeTextView.text = when (printer.connectionType) {
                com.extrotarget.extropos.ui.settings.printer.ConnectionType.USB -> "USB: ${printer.address}"
                com.extrotarget.extropos.ui.settings.printer.ConnectionType.BLUETOOTH -> "Bluetooth: ${printer.address}"
                com.extrotarget.extropos.ui.settings.printer.ConnectionType.NETWORK -> "Network: ${printer.address}${printer.port?.let { ":$it" } ?: ""}"
            }

            binding.statusIndicator.setBackgroundColor(
                if (printer.isConnected) android.graphics.Color.GREEN else android.graphics.Color.RED
            )

            binding.defaultBadge.visibility = if (printer.isDefault) android.view.View.VISIBLE else android.view.View.GONE

            binding.root.setOnClickListener { onPrinterClick(printer) }
            binding.testPrintButton.setOnClickListener { onTestPrintClick(printer) }
            binding.deleteButton.setOnClickListener { onDeleteClick(printer) }
        }
    }

    class PrinterDiffCallback : DiffUtil.ItemCallback<Printer>() {
        override fun areItemsTheSame(oldItem: Printer, newItem: Printer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Printer, newItem: Printer): Boolean {
            return oldItem == newItem
        }
    }
}