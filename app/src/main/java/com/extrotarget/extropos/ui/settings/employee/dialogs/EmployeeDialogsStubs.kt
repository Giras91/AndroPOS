package com.extrotarget.extropos.ui.settings.employee.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.extrotarget.extropos.ui.settings.employee.Employee

class AddEmployeeDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }
}

class EmployeeDetailsDialogFragment : DialogFragment() {
    companion object {
        fun newInstance(employee: Employee): EmployeeDetailsDialogFragment {
            return EmployeeDetailsDialogFragment()
        }
    }
}

class EditEmployeeDialogFragment : DialogFragment() {
    companion object {
        fun newInstance(employee: Employee): EditEmployeeDialogFragment {
            return EditEmployeeDialogFragment()
        }
    }
}
