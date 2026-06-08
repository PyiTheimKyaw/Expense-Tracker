package com.example.expense_tracker.domain.repository

import com.example.expense_tracker.domain.model.Expense
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {
    fun observeExpenses(): Flow<List<Expense>>
    suspend fun addExpense(expense: Expense)
    suspend fun deleteExpense(id: Long)
    suspend fun clearAllExpenses()
}

