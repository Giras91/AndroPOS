package com.extrotarget.extropos.ui.settings.table

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.extrotarget.extropos.databinding.DialogTableSectionBinding
import com.extrotarget.extropos.domain.model.TableSection
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class TableSectionDialogFragment : DialogFragment() {

    private var _binding: DialogTableSectionBinding? = null
    private val binding get() = _binding!!

    private var existing: TableSection? = null
    var onSaved: ((TableSection) -> Unit)? = null

    companion object {
        private const val ARG_SECTION = "arg_section"
        fun newInstance(section: TableSection?): TableSectionDialogFragment = TableSectionDialogFragment().apply {
            arguments = Bundle().apply { section?.let { putParcelable(ARG_SECTION, it) } }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        existing = arguments?.getParcelable(ARG_SECTION, TableSection::class.java)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogTableSectionBinding.inflate(layoutInflater)
        loadExisting()
        return MaterialAlertDialogBuilder(requireContext())
            .setView(binding.root)
            .setPositiveButton("Save") { _, _ -> save() }
            .setNegativeButton("Cancel", null)
            .create()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = binding.root

    private fun loadExisting() {
        existing?.let { s ->
            binding.titleText.text = "Edit Section"
            binding.nameInput.setText(s.name)
            binding.descriptionInput.setText(s.description)
            binding.colorInput.setText(s.color)
            binding.orderInput.setText(s.displayOrder.toString())
            binding.activeSwitch.isChecked = s.isActive
        } ?: run {
            binding.titleText.text = "Add Section"
        }
    }

    private fun save() {
        val name = binding.nameInput.text?.toString()?.trim()
        if (name.isNullOrEmpty()) {
            binding.nameInput.error = "Section name is required"
            return
        }
        val order = binding.orderInput.text?.toString()?.toIntOrNull() ?: 0
        val section = TableSection(
            id = existing?.id ?: "section_${System.currentTimeMillis()}",
            name = name,
            description = binding.descriptionInput.text?.toString()?.takeIf { it.isNotBlank() },
            color = binding.colorInput.text?.toString()?.takeIf { it.isNotBlank() },
            displayOrder = order,
            isActive = binding.activeSwitch.isChecked,
            createdAt = existing?.createdAt ?: System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )
        onSaved?.invoke(section)
        dismiss()
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
