package com.example.trackingapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.trackingapp.navigation.NavigationGraph
import com.example.trackingapp.navigation.Screen
import com.example.trackingapp.navigation.Screen.Auth
import com.example.trackingapp.ui.auth.RegisterScreen
import com.example.trackingapp.ui.components.BottomBar
import com.example.trackingapp.ui.theme.MyappTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.KClass

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyappTheme {
                val navController = rememberNavController()
                val startDestination = Screen.Login
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                // Rota kontrolü için yardımcı fonksiyon
                fun isCurrentScreen(screen: KClass<out Screen>): Boolean {
                    return currentDestination?.hierarchy?.any {
                        it.route?.contains(screen.simpleName ?: "") == true
                    } == true
                }

                // Login veya Register sayfalarında olup olmadığımızı kontrol eden fonksiyon
                val isAuthScreen = isCurrentScreen(Screen.Login::class) || isCurrentScreen(Screen.Register::class)

                Scaffold(
                    topBar = {
                        // Yalnızca Auth sayfalarında değilse TopBar'ı göster
                        if (!isAuthScreen) {
                            val title = when {
                                isCurrentScreen(Screen.Home::class) -> "Home Screen"
                                isCurrentScreen(Screen.List::class) -> "List Screen"
                                isCurrentScreen(Screen.Settings::class) -> "Settings"
                                isCurrentScreen(Screen.Add::class) -> "Add Expense"
                                isCurrentScreen(Screen.Detail::class) -> "Detail"
                                // Uygulamanızdaki diğer ekranları buraya ekleyebilirsiniz
                                else -> "Not Found"
                            }

                            CenterAlignedTopAppBar(
                                title = {
                                    Text(title)
                                },
                                navigationIcon = {
                                    if (!isCurrentScreen(Screen.Home::class)){
                                        IconButton(
                                            onClick = {
                                                navController.navigateUp()
                                            }
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.ArrowBack,
                                                contentDescription = "Back"
                                            )
                                        }
                                    }
                                }
                            )
                        }
                    },
                    bottomBar = {
                        // Yalnızca Auth sayfalarında değilse BottomBar'ı göster
                        if (!isAuthScreen) {
                            if (!isCurrentScreen(Screen.List::class)){
                                BottomBar(navController)
                            }
                        }
                    },
                    floatingActionButton = {
                        if (isCurrentScreen(Screen.List::class)) {
                            FloatingActionButton(
                                onClick = {
                                    navController.navigate(Screen.Add)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Add Expense"
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavigationGraph(
                        navController = navController,
                        startDestination = startDestination,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}