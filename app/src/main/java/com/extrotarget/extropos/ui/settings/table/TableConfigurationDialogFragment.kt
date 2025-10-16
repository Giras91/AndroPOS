package com.extrotarget.extropos.ui.settings.table

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.extrotarget.extropos.R
import com.extrotarget.extropos.databinding.DialogTableConfigurationBinding
import com.extrotarget.extropos.domain.model.Table
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class TableConfigurationDialogFragment : DialogFragment() {

    private var _binding: DialogTableConfigurationBinding? = null
    private val binding get() = _binding!!

    private var existingTable: Table? = null

    private val tableTypes = arrayOf("Standard", "Booth", "Bar", "Outdoor", "VIP")
    private val sections = arrayOf("Main Dining", "Private Room", "Bar Area", "Outdoor Patio", "VIP Lounge")

    companion object {
        private const val ARG_TABLE = "arg_table"

        fun newInstance(table: Table? = null): TableConfigurationDialogFragment {
            return TableConfigurationDialogFragment().apply {
                arguments = Bundle().apply {
                    table?.let { putParcelable(ARG_TABLE, it) }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        existingTable = arguments?.getParcelable(ARG_TABLE, Table::class.java)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogTableConfigurationBinding.inflate(layoutInflater)

        setupViews()
        loadExistingData()

        return MaterialAlertDialogBuilder(requireContext())
            .setView(binding.root)
            .setPositiveButton("Save") { _, _ -> saveTable() }
            .setNegativeButton("Cancel", null)
            .create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    private fun setupViews() {
        // Setup spinners
        val tableTypeAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            tableTypes
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        binding.tableTypeSpinner.adapter = tableTypeAdapter

        val sectionAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            sections
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        binding.sectionSpinner.adapter = sectionAdapter

        // Setup switches with dynamic text
        binding.smokingAllowedSwitch.setOnCheckedChangeListener { _, isChecked ->
            binding.smokingAllowedText.text = if (isChecked) "Smoking Allowed" else "No Smoking"
        }

        binding.accessibleSwitch.setOnCheckedChangeListener { _, isChecked ->
            binding.accessibleText.text = if (isChecked) "Wheelchair Accessible" else "Not Accessible"
        }

        binding.powerOutletSwitch.setOnCheckedChangeListener { _, isChecked ->
            binding.powerOutletText.text = if (isChecked) "Has Power Outlet" else "No Power Outlet"
        }
    }

    private fun loadExistingData() {
        existingTable?.let { table ->
            binding.titleText.text = "Edit Table"
            binding.tableNumberInput.setText(table.number.toString())
            binding.capacityInput.setText(table.capacity.toString())

            // Set spinner selections
            table.tableType?.let { type ->
                val index = tableTypes.indexOf(type)
                if (index >= 0) binding.tableTypeSpinner.setSelection(index)
            }

            table.section?.let { section ->
                val index = sections.indexOf(section)
                if (index >= 0) binding.sectionSpinner.setSelection(index)
            }

            // Set switches
            binding.smokingAllowedSwitch.isChecked = table.isSmokingAllowed
            binding.accessibleSwitch.isChecked = table.isAccessible
            binding.powerOutletSwitch.isChecked = table.hasPowerOutlet
            binding.reservableSwitch.isChecked = table.isReservable

            // Set business rules
            table.minimumSpendCents?.let { cents ->
                binding.minimumSpendInput.setText((cents / 100.0).toString())
            }

            table.depositRequiredCents?.let { cents ->
                binding.depositRequiredInput.setText((cents / 100.0).toString())
            }

            binding.specialNotesInput.setText(table.specialNotes)
        } ?: run {
            binding.titleText.text = "Add Table"
        }
    }

    private fun saveTable() {
        if (!validateInputs()) return

        val table = createTableFromInputs()

        // Send result back to parent fragment
        val targetFragment = targetFragment as? TableConfigurationFragment
        targetFragment?.onTableSaved(table)

        dismiss()
    }

    private fun validateInputs(): Boolean {
        var isValid = true

        // Validate table number
        val tableNumber = binding.tableNumberInput.text?.toString()?.trim()
        if (tableNumber.isNullOrEmpty()) {
            binding.tableNumberInput.error = "Table number is required"
            isValid = false
        } else {
            val number = tableNumber.toIntOrNull()
            if (number == null || number <= 0) {
                binding.tableNumberInput.error = "Please enter a valid table number"
                isValid = false
            }
        }

        // Validate capacity
        val capacity = binding.capacityInput.text?.toString()?.trim()
        if (capacity.isNullOrEmpty()) {
            binding.capacityInput.error = "Capacity is required"
            isValid = false
        } else {
            val cap = capacity.toIntOrNull()
            if (cap == null || cap <= 0) {
                binding.capacityInput.error = "Please enter a valid capacity"
                isValid = false
            }
        }

        // Validate monetary values
        val minSpend = binding.minimumSpendInput.text?.toString()?.trim()
        if (!minSpend.isNullOrEmpty()) {
            val amount = minSpend.toDoubleOrNull()
            if (amount == null || amount < 0) {
                binding.minimumSpendInput.error = "Please enter a valid amount"
                isValid = false
            }
        }

        val deposit = binding.depositRequiredInput.text?.toString()?.trim()
        if (!deposit.isNullOrEmpty()) {
            val amount = deposit.toDoubleOrNull()
            if (amount == null || amount < 0) {
                binding.depositRequiredInput.error = "Please enter a valid amount"
                isValid = false
            }
        }

        return isValid
    }

    private fun createTableFromInputs(): Table {
        val tableNumber = binding.tableNumberInput.text?.toString()?.toIntOrNull() ?: 1
        val capacity = binding.capacityInput.text?.toString()?.toIntOrNull() ?: 2

        val minSpendCents = binding.minimumSpendInput.text?.toString()?.toDoubleOrNull()?.let {
            (it * 100).toLong()
        }

        val depositCents = binding.depositRequiredInput.text?.toString()?.toDoubleOrNull()?.let {
            (it * 100).toLong()
        }

        return Table(
            id = existingTable?.id ?: "",
            number = tableNumber,
            capacity = capacity,
            status = existingTable?.status ?: com.extrotarget.extropos.domain.model.TableStatus.AVAILABLE,
            currentOrderId = existingTable?.currentOrderId,
            section = sections.getOrNull(binding.sectionSpinner.selectedItemPosition),
            tableType = tableTypes.getOrNull(binding.tableTypeSpinner.selectedItemPosition),
            positionX = existingTable?.positionX,
            positionY = existingTable?.positionY,
            width = existingTable?.width,
            height = existingTable?.height,
            rotation = existingTable?.rotation ?: 0f,
            assignedServerId = existingTable?.assignedServerId,
            lastServedAt = existingTable?.lastServedAt,
            estimatedOccupancyTime = existingTable?.estimatedOccupancyTime,
            specialNotes = binding.specialNotesInput.text?.toString()?.takeIf { it.isNotBlank() },
            isReservable = binding.reservableSwitch.isChecked,
            minimumSpendCents = minSpendCents,
            depositRequiredCents = depositCents,
            createdAt = existingTable?.createdAt ?: System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis(),
            isActive = existingTable?.isActive ?: true,
            isSmokingAllowed = binding.smokingAllowedSwitch.isChecked,
            isAccessible = binding.accessibleSwitch.isChecked,
            hasPowerOutlet = binding.powerOutletSwitch.isChecked
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}