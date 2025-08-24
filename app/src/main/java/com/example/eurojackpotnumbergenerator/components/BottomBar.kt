package com.example.eurojackpotnumbergenerator.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.R
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun BottomNavigationBar(navController: NavController) {
    val navController = navController
    val items = listOf(
        BottomNavItem("EuroJackpot", "home"),
        BottomNavItem("Sportka", "sportka")
    )

    NavigationBar {
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    when(item.route) {
                        "home" -> Icon(Icons.Default.Home, contentDescription = "EuroJackpot")
                        "sportka" -> Icon(Icons.Default.Star, contentDescription = "Sportka")
                        else -> Icon(Icons.Default.Home, contentDescription = item.title)
                    }
                },
                label = { Text(item.title) },
                selected = currentDestination == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

data class BottomNavItem(val title: String, val route: String)