package io.github.kaducmk.grupodouno.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import io.github.kaducmk.grupodouno.auth.presentation.AuthScreen

fun NavGraphBuilder.authNavGraph(navController: NavController) {
    navigation<Routes.AuthRoutes>(startDestination = Routes.AuthRoutes.ToAuth) {
        composable<Routes.AuthRoutes.ToAuth> {
             AuthScreen()
        }
    }
}