package com.example.expense_tracker.presentation.add_expense

import app.cash.turbine.test
import com.example.expense_tracker.domain.repository.ExpenseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock

@OptIn(ExperimentalCoroutinesApi::class)
class AddExpenseViewModelTest {

    private lateinit var viewModel: AddExpenseViewModel
    private val repository: ExpenseRepository = mock()
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = AddExpenseViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state is default`() = runTest {
        val state = viewModel.state.value
        assertEquals("", state.title)
        assertEquals("", state.amount)
        assertNull(state.titleError)
        assertNull(state.amountError)
    }

    @Test
    fun `saveExpense with empty title sets titleError`() = runTest {
        viewModel.onTitleChanged("")
        viewModel.saveExpense()

        assertEquals("Title cannot be empty", viewModel.state.value.titleError)
    }

    @Test
    fun `saveExpense with invalid amount sets amountError`() = runTest {
        viewModel.onTitleChanged("Coffee")
        viewModel.onAmountChanged("invalid")
        viewModel.saveExpense()

        assertEquals("Enter a valid amount", viewModel.state.value.amountError)
    }

    @Test
    fun `onTitleChanged clears titleError`() = runTest {
        viewModel.onTitleChanged("")
        viewModel.saveExpense()
        assertNotNull(viewModel.state.value.titleError)

        viewModel.onTitleChanged("New Title")
        assertNull(viewModel.state.value.titleError)
    }
}