package com.extrotarget.extropos.printer.domain.usecase

import com.extrotarget.extropos.printer.data.IPrinterRepository
import com.extrotarget.extropos.printer.domain.model.PrintJob
import javax.inject.Inject

class PrintReceiptUseCase @Inject constructor(
    private val repo: IPrinterRepository
) {
    suspend operator fun invoke(job: PrintJob) = repo.print(job)
}
