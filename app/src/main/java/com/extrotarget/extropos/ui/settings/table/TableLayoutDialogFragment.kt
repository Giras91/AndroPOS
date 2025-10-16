package com.extrotarget.extropos.ui.settings.table

import android.app.Dialog
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.extrotarget.extropos.databinding.DialogTableLayoutBinding
import com.extrotarget.extropos.domain.model.TableLayout
import java.util.UUID

class TableLayoutDialogFragment : DialogFragment() {
    var onResult: ((TableLayout) -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = DialogTableLayoutBinding.inflate(LayoutInflater.from(requireContext()))
        val existing = arguments?.getParcelable<TableLayoutArg>(ARG)?.toDomain()

        if (existing != null) {
            binding.inputName.setText(existing.name)
            binding.inputDescription.setText(existing.description ?: "")
            binding.checkboxDefault.isChecked = existing.isDefault
            binding.inputLayoutData.setText(existing.layoutData ?: "")
        }

        return AlertDialog.Builder(requireContext())
            .setTitle(if (existing == null) "Add Layout" else "Edit Layout")
            .setView(binding.root)
            .setPositiveButton("Save") { _, _ ->
                val name = binding.inputName.text?.toString()?.trim().orEmpty()
                if (name.isEmpty()) return@setPositiveButton
                val layout = TableLayout(
                    id = existing?.id ?: UUID.randomUUID().toString(),
                    name = name,
                    description = binding.inputDescription.text?.toString()?.trim().orEmpty().ifEmpty { null },
                    isDefault = binding.checkboxDefault.isChecked,
                    layoutData = binding.inputLayoutData.text?.toString()?.trim().orEmpty().ifEmpty { null },
                    createdBy = existing?.createdBy
                )
                onResult?.invoke(layout)
            }
            .setNegativeButton("Cancel", null)
            .create()
    }

    companion object {
        private const val ARG = "arg"

        fun newInstance(existing: TableLayout?): TableLayoutDialogFragment {
            val f = TableLayoutDialogFragment()
            if (existing != null) {
                f.arguments = Bundle().apply { putParcelable(ARG, TableLayoutArg.from(existing)) }
            }
            return f
        }
    }
}

// Lightweight Parcelable arg since we avoided @Parcelize for stability elsewhere
private data class TableLayoutArg(
    val id: String,
    val name: String,
    val description: String?,
    val isDefault: Boolean,
    val layoutData: String?,
    val createdBy: String?
) : Parcelable {
    constructor(p: Parcel) : this(
        p.readString() ?: "",
        p.readString() ?: "",
        p.readString(),
        p.readByte().toInt() != 0,
        p.readString(),
        p.readString()
    )
    override fun writeToParcel(p: Parcel, flags: Int) {
        p.writeString(id)
        p.writeString(name)
        p.writeString(description)
        p.writeByte(if (isDefault) 1 else 0)
        p.writeString(layoutData)
        p.writeString(createdBy)
    }
    override fun describeContents(): Int = 0
    fun toDomain() = TableLayout(id, name, description, isDefault, layoutData, createdBy)
    companion object CREATOR : Parcelable.Creator<TableLayoutArg> {
        override fun createFromParcel(p: Parcel): TableLayoutArg = TableLayoutArg(p)
        override fun newArray(size: Int): Array<TableLayoutArg?> = arrayOfNulls(size)
        fun from(d: TableLayout) = TableLayoutArg(d.id, d.name, d.description, d.isDefault, d.layoutData, d.createdBy)
    }
}
