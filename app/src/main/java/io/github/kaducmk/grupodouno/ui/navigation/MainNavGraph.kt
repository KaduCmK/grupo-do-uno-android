package io.github.kaducmk.grupodouno.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun MainNavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = ToHomeNavGraph) {
        homeNavGraph(navHostController)
    }
}