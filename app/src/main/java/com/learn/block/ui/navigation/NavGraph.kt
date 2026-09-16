package com.learn.block.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.learn.block.ui.screens.*

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "SPLASH") {
        composable("SPLASH") {
            SplashScreen { destination ->
                navController.navigate(destination) {
                    popUpTo("SPLASH") { inclusive = true }
                }
            }
        }
        composable("WELCOME") {
            WelcomeScreen(
                onCreateAccount = { navController.navigate("CREATE_ACCOUNT") },
                onLogin = { navController.navigate("LOGIN") },
                onGuest = { 
                    navController.navigate("MAIN") {
                        popUpTo("WELCOME") { inclusive = true }
                    }
                }
            )
        }
        composable("LOGIN") {
            LoginScreen(
                onBack = { navController.popBackStack() },
                onCreateAccount = { navController.navigate("CREATE_ACCOUNT") },
                onSuccess = {
                    navController.navigate("MAIN") {
                        popUpTo("WELCOME") { inclusive = true }
                    }
                }
            )
        }
        composable("CREATE_ACCOUNT") {
            CreateAccount(
                onBack = { navController.popBackStack() },
                onLoginInstead = { navController.navigate("LOGIN") },
                onSuccess = {
                    navController.navigate("MAIN") {
                        popUpTo("WELCOME") { inclusive = true }
                    }
                }
            )
        }
        composable("MAIN") {
            MainContainer()
        }
    }
}

@Composable
fun MainContainer() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "HOME"

    Scaffold(
        bottomBar = {
            // البوتم بار هيظهر في كل الصفحات داخل الـ MainContainer
            BottomBar(
                selectedRoute = when {
                    currentRoute == "DETAILS" -> "HOME" // يظل الـ Home منور لو في التفاصيل
                    currentRoute == "CATEGORY_DETAILS" -> "SHOP" // يظل الـ Shop منور
                    else -> currentRoute
                },
                onItemSelected = { route ->
                    if (currentRoute != route) {
                        navController.navigate(route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "HOME",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("HOME") { 
                HomeScreen(onProductClick = { navController.navigate("DETAILS") }) 
            }
            composable("SHOP") { 
                ShopScreen(onCategoryClick = { navController.navigate("CATEGORY_DETAILS") }) 
            }
            composable("SAVED") { 
                SavedScreen() 
            }
            composable("PROFILE") { 
                ProfilScreen() 
            }
            composable("DETAILS") {
                ProductDetiels(onBack = { navController.popBackStack() })
            }
            composable("CATEGORY_DETAILS") {
                CatageroDetals(onBack = { navController.popBackStack() }, onProductClick = { navController.navigate("DETAILS") })
            }
        }
    }
}
