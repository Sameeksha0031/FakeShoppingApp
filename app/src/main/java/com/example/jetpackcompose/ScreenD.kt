package com.example.jetpackcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.jetpackcompose.ui.theme.Palette4

@Composable
fun StartScreenD(navController: NavHostController) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Palette4),
        Alignment.Center) {
        Column {
            Text(text = "ScreenD")
            Button(onClick = {
                navController.navigate(BottomNavItem.Home.title){
                    launchSingleTop = true
                    popUpTo(BottomNavItem.Home.title)
                }
            }) {
                Text(text = "on Click")
            }
        }
    }
}