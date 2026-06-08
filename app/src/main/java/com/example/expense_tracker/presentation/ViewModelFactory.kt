package com.example.expense_tracker.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.expense_tracker.domain.repository.ExpenseRepository
import com.example.expense_tracker.presentation.add_expense.AddExpenseViewModel
import com.example.expense_tracker.presentation.expense_list.ExpenseListViewModel

class ViewModelFactory(
    private val repository: ExpenseRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ExpenseListViewModel::class.java) -> {
                ExpenseListViewModel(repository) as T
            }
            modelClass.isAssignableFrom(AddExpenseViewModel::class.java) -> {
                AddExpenseViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}