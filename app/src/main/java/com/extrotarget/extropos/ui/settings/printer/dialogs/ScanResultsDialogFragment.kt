package com.extrotarget.extropos.ui.settings.printer.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.extrotarget.extropos.ui.settings.printer.DiscoveredPrinter

class ScanResultsDialogFragment : DialogFragment() {
    
    companion object {
        fun newInstance(results: List<DiscoveredPrinter>): ScanResultsDialogFragment {
            return ScanResultsDialogFragment()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("Scan Results")
            .setMessage("Found printers will be displayed here")
            .setPositiveButton("OK", null)
            .create()
    }
}