package com.example.trackingapp.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.LocalHospital
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Receipt
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.trackingapp.domain.model.ExpenseCategory
import com.example.trackingapp.navigation.Screen
import com.example.trackingapp.ui.list.components.ListCard


@Composable
fun ListScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<ListViewModel>()
    val isUserAuthenticated = viewModel.isAuthenticated.collectAsStateWithLifecycle()
    val allExpenses = viewModel.allExpense.collectAsStateWithLifecycle()

    LaunchedEffect(isUserAuthenticated.value) {
        if (!isUserAuthenticated.value) {
            navController.navigate(Screen.Login)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (allExpenses.value.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No expenses yet",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(allExpenses.value) { expense ->
                    ListCard(
                        expense = expense,
                        onDelete = { viewModel.deleteExpense(expense.id!!) }
                    )
                }
            }
        }
    }
}



@Composable
fun getCategoryIcon(category: ExpenseCategory): ImageVector {
    return when (category) {
        ExpenseCategory.FOOD -> Icons.Outlined.Restaurant
        ExpenseCategory.SHOPPING -> Icons.Outlined.ShoppingCart
        ExpenseCategory.TRANSPORT -> Icons.Outlined.DirectionsCar
        ExpenseCategory.ENTERTAINMENT -> Icons.Outlined.Movie
        ExpenseCategory.BILLS -> Icons.Outlined.Receipt
        ExpenseCategory.HEALTH -> Icons.Outlined.LocalHospital
        ExpenseCategory.OTHER -> Icons.Outlined.MoreHoriz
    }
}