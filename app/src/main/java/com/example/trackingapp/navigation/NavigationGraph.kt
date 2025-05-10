package com.example.trackingapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.trackingapp.navigation.Screen.Tracking
import com.example.trackingapp.presentation.add.AddScreen
import com.example.trackingapp.presentation.auth.AuthScreen
import com.example.trackingapp.presentation.auth.LoginScreen
import com.example.trackingapp.presentation.auth.RegisterScreen
import com.example.trackingapp.presentation.home.HomeScreen
import com.example.trackingapp.presentation.list.ListScreen
import com.example.trackingapp.presentation.settings.SettingsScreen
import com.example.trackingapp.presentation.tracking.TrackingScreen


@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: Screen,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable<Screen.Auth> {
            AuthScreen(navController)
        }
        composable<Screen.Home> {
            HomeScreen(navController)
        }

        composable<Screen.Settings> {
            SettingsScreen(navController)
        }
        composable<Tracking> {
            TrackingScreen(navController)
        }
        composable<Screen.Add> {
            AddScreen(navController)
        }
        composable<Screen.Register> {
            RegisterScreen(navController)
        }
        composable<Screen.Login>{
            LoginScreen(navController)
        }
        composable<Screen.List>{
            ListScreen(navController)
        }
    }
}