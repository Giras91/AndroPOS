package com.extrotarget.extropos.domain.model

enum class OrderType {
    DINE_IN,
    TAKEOUT,
    DELIVERY
}

enum class OrderItemStatus {
    PENDING,
    PREPARING,
    READY,
    SERVED
}
