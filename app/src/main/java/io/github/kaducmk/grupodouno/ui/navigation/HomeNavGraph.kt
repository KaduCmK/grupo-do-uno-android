package io.github.kaducmk.grupodouno.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import io.github.kaducmk.grupodouno.home.presentation.HomeScreen

fun NavGraphBuilder.homeNavGraph(navController: NavController) {
    navigation<Routes.HomeRoutes>(startDestination = Routes.HomeRoutes.ToHome) {
        composable<Routes.HomeRoutes.ToHome> {
            HomeScreen()
        }
    }
}