package ua.zp.testtaskjungleconsalting.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import ua.zp.testtaskjungleconsalting.presentation.detailsscreen.ReposListViewModel
import ua.zp.testtaskjungleconsalting.presentation.detailsscreen.DetailsScreen
import ua.zp.testtaskjungleconsalting.presentation.startscreen.UsersListViewModel
import ua.zp.testtaskjungleconsalting.presentation.startscreen.StartScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.StartScreen.route) {

        composable(Screen.StartScreen.route) {
            val userListViewModel = hiltViewModel<UsersListViewModel>()
            val users = userListViewModel.userPagingFlow.collectAsLazyPagingItems()
            StartScreen(
                navController = navController,
                users = users
            )
        }
        composable(Screen.DetailsScreen.route) { backStackEntry ->
            val login = backStackEntry.arguments?.getString("login") ?: return@composable
            val repoListViewModel = hiltViewModel<ReposListViewModel>()

            repoListViewModel.setLogin(login)
            val repos = repoListViewModel.repoPagingFlow.collectAsLazyPagingItems()

            DetailsScreen(
                repos = repos
            )
        }
    }
}

sealed class Screen(val route: String) {
    object StartScreen : Screen("start_screen")
    object DetailsScreen : Screen("details_screen/{login}")
}