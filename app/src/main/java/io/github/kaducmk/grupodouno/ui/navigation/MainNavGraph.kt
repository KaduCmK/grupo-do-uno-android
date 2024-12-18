package io.github.kaducmk.grupodouno.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import io.github.kaducmk.grupodouno.ui.navigation.routes.HomeRoutes

@Composable
fun MainNavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = ToHomeNavGraph) {
        authNavGraph(navHostController)
        homeNavGraph(navHostController)
    }
}