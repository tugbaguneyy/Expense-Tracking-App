package com.example.trackingapp.presentation.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.trackingapp.navigation.Screen

@Composable
fun LoginScreen(navController: NavController){
    val viewModel= hiltViewModel<AuthViewModel>()
    val isUserAuthenticated = viewModel.isAuthenticated.collectAsStateWithLifecycle()

    val mail= remember { mutableStateOf("") }
    val password= remember { mutableStateOf("") }

    val errorMessage = viewModel.errorMessage.collectAsStateWithLifecycle()

    errorMessage.value?.let {
        Text(text = it, color = Color.Red)
    }


    LaunchedEffect(isUserAuthenticated.value) {
        if(isUserAuthenticated.value){
            navController.navigate(Screen.Home)
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Login")
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mail.value,
            onValueChange = { mail.value = it },
            label = { Text("Mail") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password") }
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if(mail.value.isNotEmpty() && password.value.isNotEmpty()){
                    viewModel.signIn(mail.value.trim(), password.value.trim())
                }
            }) {
            Text(text = "Login")
        }
        TextButton(
            onClick = {
                navController.navigate(Screen.Register)
            }
        ) {
            Text(text = "Register")
        }
    }
}