package com.extrotarget.extropos.reporting

enum class ReportType(val displayName: String) {
    DAILY("Daily Sales"),
    TRANSACTIONS("Transactions"),
    SHIFT("Shift Report"),
    TRANSACTION_SUMMARY("Transaction Summary"),
    SALES_OVER_TIME("Sales Over Time"),
    SALES_BY_PRODUCT("Sales By Product"),
    MONTHLY("Monthly Report");

    override fun toString(): String = displayName
}
