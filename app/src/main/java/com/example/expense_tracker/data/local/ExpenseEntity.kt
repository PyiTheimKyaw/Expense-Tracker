package com.example.expense_tracker.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String,
    val amountCents: Long,
    val dateEpochMillis: Long,
    val category: String,
    val note: String?
)

