package com.extrotarget.extropos.printer.domain.model

data class PrintJob(
    val title: String,
    val body: String,
    val copies: Int = 1
)
