package com.example.trackingapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.trackingapp.navigation.NavigationGraph
import com.example.trackingapp.navigation.Screen
import com.example.trackingapp.presentation.components.BottomBar
import com.example.trackingapp.presentation.settings.SettingsViewModel
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
            val viewModel = hiltViewModel<SettingsViewModel>()
            val isDarkMode = viewModel.isDarkMode.collectAsStateWithLifecycle()
            MyappTheme(
                darkTheme = isDarkMode.value
            ) {
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
                                isCurrentScreen(Screen.Home::class) -> "Home"
                                isCurrentScreen(Screen.List::class) -> "Harcamalar"
                                isCurrentScreen(Screen.Settings::class) -> "Ayarlar"
                                isCurrentScreen(Screen.Add::class) -> "Harcama Ekle"
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
                                                imageVector = Icons.Default.ArrowBackIosNew,
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