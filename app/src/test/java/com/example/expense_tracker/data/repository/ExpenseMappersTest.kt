package com.example.expense_tracker.data.repository

import com.example.expense_tracker.data.local.ExpenseEntity
import com.example.expense_tracker.domain.model.Expense
import org.junit.Assert.assertEquals
import org.junit.Test

class ExpenseMappersTest {

    @Test
    fun `toDomain maps entity to domain model correctly`() {
        val entity = ExpenseEntity(
            id = 1L,
            title = "Lunch",
            amountCents = 1500,
            dateEpochMillis = 123456789L,
            category = "Food",
            note = "Burger King"
        )

        val domain = entity.toDomain()

        assertEquals(entity.id, domain.id)
        assertEquals(entity.title, domain.title)
        assertEquals(entity.amountCents, domain.amountCents)
        assertEquals(entity.dateEpochMillis, domain.dateEpochMillis)
        assertEquals(entity.category, domain.category)
        assertEquals(entity.note, domain.note)
    }

    @Test
    fun `toEntity maps domain to entity model correctly`() {
        val domain = Expense(
            id = 1L,
            title = "Lunch",
            amountCents = 1500,
            dateEpochMillis = 123456789L,
            category = "Food",
            note = "Burger King"
        )

        val entity = domain.toEntity()

        assertEquals(domain.id, entity.id)
        assertEquals(domain.title, entity.title)
        assertEquals(domain.amountCents, entity.amountCents)
        assertEquals(domain.dateEpochMillis, entity.dateEpochMillis)
        assertEquals(domain.category, entity.category)
        assertEquals(domain.note, entity.note)
    }
}