package com.example.trackingapp.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.trackingapp.navigation.Screen

@Composable
fun HomeScreen(
    navController : NavController
) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val isUserAuthenticated = viewModel.isAuthenticated.collectAsStateWithLifecycle()

    val allExpenses = viewModel.allExpense.collectAsStateWithLifecycle()

    LaunchedEffect(isUserAuthenticated.value) {
        if (!isUserAuthenticated.value){
            navController.navigate(Screen.Login)
        }
    }

//    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//        Text(text = "Home", fontSize = 24.sp)
//        Button(
//            onClick = {
//                viewModel.signOut()
//            }
//        ) {
//            Text(text = "Sign Out")
//        }
//    }

    LazyColumn (Modifier.fillMaxSize()){
        items(allExpenses.value.size) {
            Card(modifier = Modifier.fillMaxWidth().padding(5.dp)) {
                Text(text = allExpenses.value[it].title)
                Text(text = allExpenses.value[it].description)
                Text(text = allExpenses.value[it].amount.toString())
            }
        }
    }

    Button(
        onClick = {
            navController.navigate(Screen.Add)
        }
    ) {
        Text("Add")
    }
}