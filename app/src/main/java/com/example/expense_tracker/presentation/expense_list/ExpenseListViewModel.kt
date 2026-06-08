package com.example.expense_tracker.presentation.expense_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expense_tracker.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ExpenseListViewModel(
    private val repository: ExpenseRepository
) : ViewModel() {

    private val _state = MutableStateFlow<ExpenseListState>(ExpenseListState.Loading)
    val state: StateFlow<ExpenseListState> = _state.asStateFlow()

    init {
        observeExpenses()
    }

    fun deleteExpense(id: Long) {
        viewModelScope.launch {
            try {
                repository.deleteExpense(id)
            } catch (e: Exception) {
                _state.value = ExpenseListState.Error(e.message ?: "Failed to delete expense")
            }
        }
    }

    fun clearAllExpenses() {
        viewModelScope.launch {
            try {
                repository.clearAllExpenses()
            } catch (e: Exception) {
                _state.value = ExpenseListState.Error(e.message ?: "Failed to clear expenses")
            }
        }
    }

    private fun observeExpenses() {
        repository.observeExpenses()
            .onEach { expenses ->
                if (expenses.isEmpty()) {
                    _state.value = ExpenseListState.Empty
                } else {
                    val total = expenses.sumOf { it.amountCents }
                    _state.value = ExpenseListState.Success(expenses, total)
                }
            }
            .catch { e ->
                _state.value = ExpenseListState.Error(e.message ?: "An unknown error occurred")
            }
            .launchIn(viewModelScope)
    }
}
