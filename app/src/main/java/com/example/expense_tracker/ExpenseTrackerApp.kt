package com.example.expense_tracker

import android.app.Application
import androidx.room.Room
import com.example.expense_tracker.data.local.ExpenseDatabase
import com.example.expense_tracker.data.repository.ExpenseRepositoryImpl
import com.example.expense_tracker.domain.repository.ExpenseRepository

class ExpenseTrackerApp : Application() {

    private val database by lazy {
        Room.databaseBuilder(
            this,
            ExpenseDatabase::class.java,
            "expense_database"
        ).build()
    }

    val repository: ExpenseRepository by lazy {
        ExpenseRepositoryImpl(database.expenseDao())
    }
}