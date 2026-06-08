package com.example.expense_tracker.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object ExpenseList : Route

    @Serializable
    data object AddExpense : Route
}