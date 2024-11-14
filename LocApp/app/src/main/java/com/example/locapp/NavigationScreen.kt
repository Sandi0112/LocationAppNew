package com.example.locapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NavigationScreen(
    viewModel: LocationViewModel,
    modifier: Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Graph.MAIN_SCREEN,
        modifier = modifier
    ) {
        composable(route = Graph.MAIN_SCREEN) {
            MainScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Graph.SAVED_LOCATIONS_SCREEN) {
            SavedLocationScreen(viewModel = viewModel)
        }
    }
}

object Graph {
    const val MAIN_SCREEN = "main_screen"
    const val SAVED_LOCATIONS_SCREEN = "saved_locations_screen"
}
