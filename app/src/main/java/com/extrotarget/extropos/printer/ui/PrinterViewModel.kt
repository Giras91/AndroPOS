package com.extrotarget.extropos.printer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extrotarget.extropos.printer.domain.model.PrintJob
import com.extrotarget.extropos.printer.domain.usecase.PrintReceiptUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrinterViewModel @Inject constructor(
    private val printReceipt: PrintReceiptUseCase
) : ViewModel() {

    private val _status = MutableStateFlow<String>("idle")
    val status: StateFlow<String> = _status

    fun print(job: PrintJob) {
        viewModelScope.launch {
            _status.value = "printing"
            val res = printReceipt(job)
            _status.value = if (res.isSuccess) "done" else "error"
        }
    }
}
