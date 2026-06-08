package com.example.expense_tracker.presentation.expense_list

import com.example.expense_tracker.domain.model.Expense

sealed interface ExpenseListState {
    object Loading : ExpenseListState
    object Empty : ExpenseListState
    data class Success(
        val expenses: List<Expense>,
        val totalAmountCents: Long
    ) : ExpenseListState
    data class Error(val message: String) : ExpenseListState
}
