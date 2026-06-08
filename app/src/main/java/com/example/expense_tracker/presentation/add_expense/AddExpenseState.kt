package com.example.expense_tracker.presentation.add_expense

data class AddExpenseState(
    val title: String = "",
    val titleError: String? = null,
    val amount: String = "",
    val amountError: String? = null,
    val category: String = "General",
    val note: String = "",
    val dateEpochMillis: Long = System.currentTimeMillis(),
    val isSaving: Boolean = false
)
