package com.example.trackingapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.trackingapp.navigation.Screen.Auth
import com.example.trackingapp.navigation.Screen.Home
import com.example.trackingapp.navigation.Screen.DetailSettings
import com.example.trackingapp.navigation.Screen.Tracking
import com.example.trackingapp.navigation.Screen.Add


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
        composable<Auth> {

        }
        composable<Home> {

        }
        composable<DetailSettings> {

        }
        composable<Tracking> {

        }
        composable<Add> {

        }
    }
}