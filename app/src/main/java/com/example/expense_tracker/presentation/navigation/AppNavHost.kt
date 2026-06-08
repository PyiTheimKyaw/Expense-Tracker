package com.example.expense_tracker.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.expense_tracker.ExpenseTrackerApp
import com.example.expense_tracker.presentation.ViewModelFactory
import com.example.expense_tracker.presentation.add_expense.AddExpenseScreen
import com.example.expense_tracker.presentation.add_expense.AddExpenseViewModel
import com.example.expense_tracker.presentation.expense_list.ExpenseListScreen
import com.example.expense_tracker.presentation.expense_list.ExpenseListViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current.applicationContext as ExpenseTrackerApp
    val repository = context.repository
    val factory = ViewModelFactory(repository)

    NavHost(
        navController = navController,
        startDestination = Route.ExpenseList,
        modifier = modifier
    ) {
        composable<Route.ExpenseList> {
            val viewModel: ExpenseListViewModel = viewModel(factory = factory)
            ExpenseListScreen(
                viewModel = viewModel,
                onAddExpenseClick = { navController.navigate(Route.AddExpense) }
            )
        }
        composable<Route.AddExpense> {
            val viewModel: AddExpenseViewModel = viewModel(factory = factory)
            AddExpenseScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}