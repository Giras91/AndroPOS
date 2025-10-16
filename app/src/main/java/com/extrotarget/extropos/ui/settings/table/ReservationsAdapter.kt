package com.extrotarget.extropos.ui.settings.table

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.extrotarget.extropos.databinding.ItemReservationBinding
import com.extrotarget.extropos.domain.model.Reservation
import com.extrotarget.extropos.domain.model.ReservationStatus
import java.text.SimpleDateFormat
import java.util.*

class ReservationsAdapter(
    private val onEditClick: (Reservation) -> Unit,
    private val onDeleteClick: (Reservation) -> Unit,
    private val onStatusChangeClick: (Reservation) -> Unit
) : ListAdapter<Reservation, ReservationsAdapter.ReservationViewHolder>(Diff()) {

    private val dateFormat = SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault())
    private val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        val binding = ItemReservationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReservationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) = holder.bind(getItem(position))

    inner class ReservationViewHolder(private val binding: ItemReservationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(reservation: Reservation) {
            binding.customerNameText.text = reservation.customerName
            binding.tableNumberText.text = "Table ${reservation.tableNumber}"
            binding.partySizeText.text = "${reservation.partySize} guests"
            binding.dateTimeText.text = dateFormat.format(Date(reservation.reservationDateTime))
            binding.durationText.text = "${reservation.durationMinutes} min"

            // Status with color coding
            binding.statusText.apply {
                text = reservation.status.name
                setTextColor(getStatusColor(reservation.status))
            }

            // Contact info
            binding.contactText.apply {
                val contact = listOfNotNull(
                    reservation.customerPhone,
                    reservation.customerEmail
                ).joinToString(" • ")
                text = contact
                visibility = if (contact.isBlank()) View.GONE else View.VISIBLE
            }

            // Special requests
            binding.specialRequestsText.apply {
                text = reservation.specialRequests
                visibility = if (reservation.specialRequests.isNullOrBlank()) View.GONE else View.VISIBLE
            }

            // Deposit info
            binding.depositText.apply {
                if (reservation.depositRequired) {
                    val amount = if (reservation.depositAmountCents > 0) {
                        "RM ${(reservation.depositAmountCents / 100.0).toString()}"
                    } else "Required"
                    text = if (reservation.depositPaid) "Deposit Paid: $amount" else "Deposit Due: $amount"
                    visibility = View.VISIBLE
                } else {
                    visibility = View.GONE
                }
            }

            binding.editButton.setOnClickListener { onEditClick(reservation) }
            binding.deleteButton.setOnClickListener { onDeleteClick(reservation) }
            binding.statusButton.setOnClickListener { onStatusChangeClick(reservation) }
            binding.root.setOnClickListener { onEditClick(reservation) }
        }

        private fun getStatusColor(status: ReservationStatus): Int {
            return when (status) {
                ReservationStatus.CONFIRMED -> 0xFF4CAF50.toInt() // Green
                ReservationStatus.PENDING -> 0xFFFF9800.toInt() // Orange
                ReservationStatus.SEATED -> 0xFF2196F3.toInt() // Blue
                ReservationStatus.COMPLETED -> 0xFF9C27B0.toInt() // Purple
                ReservationStatus.CANCELLED -> 0xFFF44336.toInt() // Red
                ReservationStatus.NO_SHOW -> 0xFF9E9E9E.toInt() // Grey
                ReservationStatus.WAITLIST -> 0xFFFF5722.toInt() // Deep Orange
            }
        }
    }

    private class Diff : DiffUtil.ItemCallback<Reservation>() {
        override fun areItemsTheSame(oldItem: Reservation, newItem: Reservation) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Reservation, newItem: Reservation) = oldItem == newItem
    }
}