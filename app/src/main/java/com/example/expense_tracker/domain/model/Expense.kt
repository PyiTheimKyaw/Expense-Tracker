package com.example.expense_tracker.domain.model

data class Expense(
    val id: Long = 0L,
    val title: String,
    val amountCents: Long,
    val dateEpochMillis: Long,
    val category: String,
    val note: String?
)

