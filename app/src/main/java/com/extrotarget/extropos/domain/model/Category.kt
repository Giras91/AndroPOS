package com.extrotarget.extropos.domain.model

data class Category(
    val id: String,
    val name: String,
    val description: String = "",
    val imageUrl: String = ""
)