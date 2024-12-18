package io.github.kaducmk.grupodouno.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import io.github.kaducmk.grupodouno.auth.presentation.AuthScreen
import io.github.kaducmk.grupodouno.ui.navigation.routes.AuthRoutes
import kotlinx.serialization.Serializable

@Serializable
object ToAuthNavGraph

fun NavGraphBuilder.authNavGraph(navController: NavController) {
    navigation<ToAuthNavGraph>(startDestination = AuthRoutes.ToAuth) {
        composable<AuthRoutes.ToAuth> {
             AuthScreen(navController = navController)
        }
    }
}