package com.example.trackingapp.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.trackingapp.navigation.Screen
import com.example.trackingapp.presentation.home.components.DailySummaryCard
import com.example.trackingapp.presentation.home.components.TransactionItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController
) {

    val viewModel = hiltViewModel<HomeViewModel>()

    val expenses = viewModel.expenses.collectAsStateWithLifecycle()
    val dailyTotal = viewModel.dailyTotal.collectAsStateWithLifecycle()
    val isAuthenticated = viewModel.isAuthenticated.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        if (!isAuthenticated.value){
            navController.navigate(Screen.Login)
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Daily Summary Card
        item {
            DailySummaryCard(dailyTotal = dailyTotal.value)
        }

//        // Quick Actions
//        item {
//            QuickActionsRow(navController = navController)
//        }

        // Recent Transactions Header
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Son Harcamalar",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                TextButton(
                    onClick = { navController.navigate(Screen.List) }
                ) {
                    Text("Tümünü Gör")
                    Icon(
                        Icons.Default.KeyboardArrowRight,
                        contentDescription = null,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }

        // Recent Transactions List
        items(
            items = expenses.value.take(5)
        ) { expense->
            TransactionItem(expense=expense)
        }
    }
}