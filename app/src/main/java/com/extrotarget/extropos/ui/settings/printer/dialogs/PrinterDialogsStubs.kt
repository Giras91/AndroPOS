package com.extrotarget.extropos.ui.settings.printer.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.extrotarget.extropos.ui.settings.printer.Printer
import com.extrotarget.extropos.ui.settings.printer.DiscoveredPrinter

class PrinterDetailsDialogFragment : DialogFragment() {
    companion object {
        fun newInstance(printer: Printer): PrinterDetailsDialogFragment {
            return PrinterDetailsDialogFragment()
        }
    }
}

class ScanResultsDialogFragment : DialogFragment() {
    companion object {
        fun newInstance(results: List<DiscoveredPrinter>): ScanResultsDialogFragment {
            return ScanResultsDialogFragment()
        }
    }
}
