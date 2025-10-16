package com.extrotarget.extropos.ui.settings.table

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.extrotarget.extropos.databinding.DialogReservationBinding
import com.extrotarget.extropos.domain.model.Reservation
import com.extrotarget.extropos.domain.model.ReservationStatus
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.util.*

class ReservationDialogFragment : DialogFragment() {

    private var _binding: DialogReservationBinding? = null
    private val binding get() = _binding!!

    private var existing: Reservation? = null
    var onSaved: ((Reservation) -> Unit)? = null

    private val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
    private val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    private var selectedDateTime: Long = System.currentTimeMillis()

    companion object {
        private const val ARG_RESERVATION = "arg_reservation"
        fun newInstance(reservation: Reservation?): ReservationDialogFragment = ReservationDialogFragment().apply {
            arguments = Bundle().apply { reservation?.let { putParcelable(ARG_RESERVATION, it) } }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        existing = arguments?.getParcelable(ARG_RESERVATION, Reservation::class.java)
        existing?.let { selectedDateTime = it.reservationDateTime }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): android.app.Dialog {
        _binding = DialogReservationBinding.inflate(layoutInflater)
        setupDateTimePickers()
        loadExisting()
        return MaterialAlertDialogBuilder(requireContext())
            .setView(binding.root)
            .setPositiveButton("Save") { _, _ -> save() }
            .setNegativeButton("Cancel", null)
            .create()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = binding.root

    private fun setupDateTimePickers() {
        binding.dateInput.setOnClickListener { showDatePicker() }
        binding.timeInput.setOnClickListener { showTimePicker() }
        updateDateTimeDisplay()
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance().apply { timeInMillis = selectedDateTime }
        DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                val newCalendar = Calendar.getInstance().apply {
                    timeInMillis = selectedDateTime
                    set(Calendar.YEAR, year)
                    set(Calendar.MONTH, month)
                    set(Calendar.DAY_OF_MONTH, day)
                }
                selectedDateTime = newCalendar.timeInMillis
                updateDateTimeDisplay()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance().apply { timeInMillis = selectedDateTime }
        TimePickerDialog(
            requireContext(),
            { _, hour, minute ->
                val newCalendar = Calendar.getInstance().apply {
                    timeInMillis = selectedDateTime
                    set(Calendar.HOUR_OF_DAY, hour)
                    set(Calendar.MINUTE, minute)
                }
                selectedDateTime = newCalendar.timeInMillis
                updateDateTimeDisplay()
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        ).show()
    }

    private fun updateDateTimeDisplay() {
        binding.dateInput.setText(dateFormat.format(Date(selectedDateTime)))
        binding.timeInput.setText(timeFormat.format(Date(selectedDateTime)))
    }

    private fun loadExisting() {
        existing?.let { r ->
            binding.titleText.text = "Edit Reservation"
            binding.customerNameInput.setText(r.customerName)
            binding.customerPhoneInput.setText(r.customerPhone)
            binding.customerEmailInput.setText(r.customerEmail)
            binding.tableNumberInput.setText(r.tableNumber)
            binding.partySizeInput.setText(r.partySize.toString())
            binding.durationInput.setText(r.durationMinutes.toString())
            binding.specialRequestsInput.setText(r.specialRequests)
            binding.depositRequiredSwitch.isChecked = r.depositRequired
            binding.depositAmountInput.setText((r.depositAmountCents / 100.0).toString())
            binding.depositPaidSwitch.isChecked = r.depositPaid
            binding.notesInput.setText(r.notes)
            updateDateTimeDisplay()
        } ?: run {
            binding.titleText.text = "Add Reservation"
            binding.depositRequiredSwitch.isChecked = false
            binding.depositPaidSwitch.isChecked = false
        }
    }

    private fun save() {
        val customerName = binding.customerNameInput.text?.toString()?.trim()
        if (customerName.isNullOrEmpty()) {
            binding.customerNameInput.error = "Customer name is required"
            return
        }

        val tableNumber = binding.tableNumberInput.text?.toString()?.trim()
        if (tableNumber.isNullOrEmpty()) {
            binding.tableNumberInput.error = "Table number is required"
            return
        }

        val partySize = binding.partySizeInput.text?.toString()?.toIntOrNull()
        if (partySize == null || partySize <= 0) {
            binding.partySizeInput.error = "Valid party size is required"
            return
        }

        val duration = binding.durationInput.text?.toString()?.toIntOrNull() ?: 120

        val depositAmount = if (binding.depositRequiredSwitch.isChecked) {
            binding.depositAmountInput.text?.toString()?.toDoubleOrNull()?.let { (it * 100).toLong() } ?: 0L
        } else 0L

        val reservation = Reservation(
            id = existing?.id ?: "reservation_${System.currentTimeMillis()}",
            tableId = "table_${tableNumber}", // This should be looked up from actual table
            tableNumber = tableNumber,
            customerName = customerName,
            customerPhone = binding.customerPhoneInput.text?.toString()?.trim(),
            customerEmail = binding.customerEmailInput.text?.toString()?.trim(),
            partySize = partySize,
            reservationDateTime = selectedDateTime,
            durationMinutes = duration,
            status = existing?.status ?: ReservationStatus.PENDING,
            specialRequests = binding.specialRequestsInput.text?.toString()?.trim(),
            depositRequired = binding.depositRequiredSwitch.isChecked,
            depositAmountCents = depositAmount,
            depositPaid = binding.depositPaidSwitch.isChecked,
            notes = binding.notesInput.text?.toString()?.trim(),
            createdBy = existing?.createdBy ?: "system",
            assignedServerId = existing?.assignedServerId,
            createdAt = existing?.createdAt ?: System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )

        onSaved?.invoke(reservation)
        dismiss()
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}