package com.extrotarget.extropos.domain.model

data class Table(
    val id: String,
    val number: Int,
    val capacity: Int,
    val status: TableStatus = TableStatus.AVAILABLE,
    val currentOrderId: String? = null
)

enum class TableStatus {
    AVAILABLE,
    OCCUPIED,
    RESERVED,
    OUT_OF_ORDER
}