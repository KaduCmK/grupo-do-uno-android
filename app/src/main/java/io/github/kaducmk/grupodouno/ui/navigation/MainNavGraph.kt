package io.github.kaducmk.grupodouno.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun MainNavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Routes.HomeRoutes.ToHome) {
        authNavGraph(navHostController)
        homeNavGraph(navHostController)
    }
}