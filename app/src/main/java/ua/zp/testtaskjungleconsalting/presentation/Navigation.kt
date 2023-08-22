package ua.zp.testtaskjungleconsalting.presentation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ua.zp.testtaskjungleconsalting.UsersListViewModel

@Composable
fun Navigation(viewModel: UsersListViewModel, context: Context) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.StartScreen.route) {

        composable(Screen.StartScreen.route) {
            StartScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(Screen.DetailsScreen.route) {
            DetailsScreen(
                navController = navController,
                viewModel = viewModel,
                context = context
            )
        }
    }
}

sealed class Screen(val route: String) {
    object StartScreen : Screen("start_screen")
    object DetailsScreen : Screen("details_screen")
}