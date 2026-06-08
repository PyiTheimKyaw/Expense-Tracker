package com.example.expense_tracker.data.repository

import com.example.expense_tracker.data.local.ExpenseDao
import com.example.expense_tracker.domain.model.Expense
import com.example.expense_tracker.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ExpenseRepositoryImpl(
    private val expenseDao: ExpenseDao
) : ExpenseRepository {

    override fun observeExpenses(): Flow<List<Expense>> =
        expenseDao.observeExpenses().map { entities ->
            entities.map { it.toDomain() }
        }

    override suspend fun addExpense(expense: Expense) {
        expenseDao.insertExpense(expense.toEntity())
    }

    override suspend fun deleteExpense(id: Long) {
        expenseDao.deleteExpense(id)
    }

    override suspend fun clearAllExpenses() {
        expenseDao.clearAll()
    }
}

