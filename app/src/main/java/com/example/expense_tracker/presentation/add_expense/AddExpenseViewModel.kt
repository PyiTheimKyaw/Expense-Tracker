package com.example.expense_tracker.presentation.add_expense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expense_tracker.domain.model.Expense
import com.example.expense_tracker.domain.repository.ExpenseRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AddExpenseViewModel(
    private val repository: ExpenseRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AddExpenseState())
    val state: StateFlow<AddExpenseState> = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    sealed interface UiEvent {
        object SaveSuccess : UiEvent
        data class ShowSnackbar(val message: String) : UiEvent
    }

    fun onTitleChanged(title: String) {
        _state.update { it.copy(title = title, titleError = null) }
    }

    fun onAmountChanged(amount: String) {
        _state.update { it.copy(amount = amount, amountError = null) }
    }

    fun onCategoryChanged(category: String) {
        _state.update { it.copy(category = category) }
    }

    fun onNoteChanged(note: String) {
        _state.update { it.copy(note = note) }
    }

    fun onDateChanged(dateMillis: Long) {
        _state.update { it.copy(dateEpochMillis = dateMillis) }
    }

    fun saveExpense() {
        val title = _state.value.title
        val amountStr = _state.value.amount
        
        var hasError = false
        if (title.isBlank()) {
            _state.update { it.copy(titleError = "Title cannot be empty") }
            hasError = true
        }

        val amountCents = amountStr.toDoubleOrNull()?.let { (it * 100).toLong() }
        if (amountCents == null || amountCents <= 0) {
            _state.update { it.copy(amountError = "Enter a valid amount") }
            hasError = true
        }

        if (hasError) return

        viewModelScope.launch {
            _state.update { it.copy(isSaving = true) }
            try {
                repository.addExpense(
                    Expense(
                        title = title,
                        amountCents = amountCents!!,
                        dateEpochMillis = _state.value.dateEpochMillis,
                        category = _state.value.category,
                        note = _state.value.note.ifBlank { null }
                    )
                )
                _uiEvent.send(UiEvent.SaveSuccess)
            } catch (e: Exception) {
                _uiEvent.send(UiEvent.ShowSnackbar(e.message ?: "Failed to save expense"))
            } finally {
                _state.update { it.copy(isSaving = false) }
            }
        }
    }
}
