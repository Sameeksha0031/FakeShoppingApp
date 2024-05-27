package com.example.jetpackcompose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraphComposable(
    navController: NavHostController,
    innerPadding: PaddingValues,
    checkBottomBarVisibility: (Boolean) -> Unit
) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.title) {
        composable(BottomNavItem.Home.title) {
            checkBottomBarVisibility(true)
            Greeting(navController)
        }

        composable(BottomNavItem.List.title) {
            checkBottomBarVisibility(false)
            StartScreenB(navController,innerPadding)
        }

        composable(BottomNavItem.Profile.title) {
            checkBottomBarVisibility(true)
            StartScreenC(navController)
        }
        
        composable(BottomNavItem.Analytics.title) {
            checkBottomBarVisibility(true)
            StartScreenD(navController = navController)
        }
    }
}