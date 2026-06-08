package com.example.expense_tracker.data.repository

import com.example.expense_tracker.data.local.ExpenseEntity
import com.example.expense_tracker.domain.model.Expense

internal fun ExpenseEntity.toDomain(): Expense =
    Expense(
        id = id,
        title = title,
        amountCents = amountCents,
        dateEpochMillis = dateEpochMillis,
        category = category,
        note = note
    )

internal fun Expense.toEntity(): ExpenseEntity =
    ExpenseEntity(
        id = id,
        title = title,
        amountCents = amountCents,
        dateEpochMillis = dateEpochMillis,
        category = category,
        note = note
    )

