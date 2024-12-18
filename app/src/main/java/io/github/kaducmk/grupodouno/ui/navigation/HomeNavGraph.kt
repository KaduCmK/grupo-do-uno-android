package io.github.kaducmk.grupodouno.ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import io.github.kaducmk.grupodouno.home.presentation.HomeScreen
import io.github.kaducmk.grupodouno.home.presentation.HomeViewModel
import io.github.kaducmk.grupodouno.ui.navigation.routes.HomeRoutes
import kotlinx.serialization.Serializable

@Serializable
object ToHomeNavGraph

fun NavGraphBuilder.homeNavGraph(navController: NavController) {
    navigation<ToHomeNavGraph>(startDestination = HomeRoutes.ToHome) {

        composable<HomeRoutes.ToHome> { backStack ->
            val parent = remember(backStack) { navController.getBackStackEntry<ToHomeNavGraph>() }
            val viewModel = hiltViewModel<HomeViewModel>(parent)
            HomeScreen(
                navController = navController,
                uiState = viewModel.uiState.collectAsState().value,
                uiEvent = viewModel::uiEvent
            )
        }
    }
}