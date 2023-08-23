package ua.zp.testtaskjungleconsalting.presentation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ua.zp.testtaskjungleconsalting.ReposListViewModel
import ua.zp.testtaskjungleconsalting.UsersListViewModel

@Composable
fun Navigation(userListViewModel: UsersListViewModel, reposListViewModel: ReposListViewModel, context: Context) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.StartScreen.route) {

        composable(Screen.StartScreen.route) {
            StartScreen(
                navController = navController,
                viewModel = userListViewModel,
            )
        }
        composable(Screen.DetailsScreen.route) { backStackEntry ->
            val login = backStackEntry.arguments?.getString("login")
            if (login != null) {
                DetailsScreen(
                    viewModel = reposListViewModel,
                    login = login
                )
            }
        }
    }
}

sealed class Screen(val route: String) {
    object StartScreen : Screen("start_screen")
    object DetailsScreen : Screen("details_screen/{login}")
}