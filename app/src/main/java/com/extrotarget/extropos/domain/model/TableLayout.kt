package com.extrotarget.extropos.domain.model

data class TableLayout(
    val id: String,
    val name: String,
    val description: String? = null,
    val isDefault: Boolean = false,
    val layoutData: String? = null,
    val createdBy: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
