package com.extrotarget.extropos.ui.settings.employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.extrotarget.extropos.databinding.FragmentEmployeeManagementBinding
import com.extrotarget.extropos.ui.settings.employee.adapters.EmployeeAdapter
import com.extrotarget.extropos.ui.settings.employee.dialogs.AddEmployeeDialogFragment
import com.extrotarget.extropos.ui.settings.employee.dialogs.EmployeeDetailsDialogFragment
import com.extrotarget.extropos.ui.settings.employee.dialogs.EditEmployeeDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EmployeeManagementFragment : Fragment() {

    private var _binding: FragmentEmployeeManagementBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EmployeeManagementViewModel by viewModels()
    private lateinit var employeeAdapter: EmployeeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmployeeManagementBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupRecyclerView()
        observeViewModel()
        loadEmployees()
    }

    private fun setupUI() {
        binding.toolbar.title = "Employee Management"
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.addEmployeeFab.setOnClickListener {
            showAddEmployeeDialog()
        }
    }

    private fun setupRecyclerView() {
        employeeAdapter = EmployeeAdapter(
            onEmployeeClick = { employee ->
                showEmployeeDetailsDialog(employee)
            },
            onEditClick = { employee ->
                showEditEmployeeDialog(employee)
            },
            onDeleteClick = { employee ->
                showDeleteConfirmationDialog(employee)
            }
        )

        binding.employeesRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = employeeAdapter
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.employees.collect { employees ->
                        employeeAdapter.submitList(employees)
                        binding.emptyStateTextView.visibility = if (employees.isEmpty()) View.VISIBLE else View.GONE
                    }
                }

                launch {
                    viewModel.isLoading.collect { isLoading ->
                        binding.loadingProgressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
                    }
                }
            }
        }
    }

    private fun loadEmployees() {
        viewModel.loadEmployees()
    }

    private fun showAddEmployeeDialog() {
        val dialog = AddEmployeeDialogFragment()
        dialog.show(childFragmentManager, "AddEmployeeDialog")
    }

    private fun showEmployeeDetailsDialog(employee: Employee) {
        val dialog = EmployeeDetailsDialogFragment.newInstance(employee)
        dialog.show(childFragmentManager, "EmployeeDetailsDialog")
    }

    private fun showEditEmployeeDialog(employee: Employee) {
        val dialog = EditEmployeeDialogFragment.newInstance(employee)
        dialog.show(childFragmentManager, "EditEmployeeDialog")
    }

    private fun showDeleteConfirmationDialog(employee: Employee) {
        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Delete Employee")
            .setMessage("Are you sure you want to delete ${employee.name}?")
            .setPositiveButton("Delete") { _, _ ->
                viewModel.deleteEmployee(employee)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}