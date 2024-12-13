package com.example.proyectofinalmovilescalculosfinacieros.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinalmovilescalculosfinacieros.ui.*
import com.example.proyectofinalmovilescalculosfinacieros.viewmodel.*

@Composable
fun FinancialApp() {
    val navController = rememberNavController()
    val historyViewModel: HistoryViewModel = viewModel()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(
                onNavigateToFinancial = { navController.navigate("product") },
                onNavigateToEmployee = { navController.navigate("employee") },
                onNavigateToEmployer = { navController.navigate("employer") }
            )
        }

        composable("product") {
            val productViewModel: ProductViewModel = viewModel(factory = ProductViewModelFactory(historyViewModel))
            ProductScreen(viewModel = productViewModel, onNavigateToHistory = {
                navController.navigate("history")
            },
                onNavigateBack = { navController.navigateUp() })
        }

        composable("employee") {
            val employeeViewModel: EmployeeViewModel = viewModel(factory = EmployeeViewModelFactory(historyViewModel))
            EmployeeScreen(viewModel = employeeViewModel, onNavigateToHistory = {
                navController.navigate("history")
            },
                onNavigateBack = { navController.navigateUp() })
        }

        composable("employer") {
            val employerViewModel: EmployerViewModel = viewModel(factory = EmployerViewModelFactory(historyViewModel))
            EmployerScreen(viewModel = employerViewModel, onNavigateToHistory = {
                navController.navigate("history")
            },
                onNavigateBack = { navController.navigateUp() })
        }

        composable("history") {
            HistoryScreen(viewModel = historyViewModel) {
                navController.navigateUp()
            }
        }
    }
}