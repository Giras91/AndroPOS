package com.extrotarget.extropos.ui.settings.employee.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.extrotarget.extropos.databinding.ItemEmployeeBinding
import com.extrotarget.extropos.ui.settings.employee.Employee

class EmployeeAdapter(
    private val onEmployeeClick: (Employee) -> Unit,
    private val onEditClick: (Employee) -> Unit,
    private val onDeleteClick: (Employee) -> Unit
) : ListAdapter<Employee, EmployeeAdapter.EmployeeViewHolder>(EmployeeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val binding = ItemEmployeeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return EmployeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class EmployeeViewHolder(private val binding: ItemEmployeeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(employee: Employee) {
            binding.employeeNameTextView.text = employee.name
            binding.employeeEmailTextView.text = employee.email
            binding.employeeRoleTextView.text = employee.role.name.lowercase().replaceFirstChar { it.uppercase() }
            binding.employeePhoneTextView.text = employee.phone ?: "No phone"

            binding.statusIndicator.setBackgroundColor(
                if (employee.isActive) android.graphics.Color.GREEN else android.graphics.Color.GRAY
            )

            binding.root.setOnClickListener { onEmployeeClick(employee) }
            binding.editButton.setOnClickListener { onEditClick(employee) }
            binding.deleteButton.setOnClickListener { onDeleteClick(employee) }
        }
    }

    class EmployeeDiffCallback : DiffUtil.ItemCallback<Employee>() {
        override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem == newItem
        }
    }
}