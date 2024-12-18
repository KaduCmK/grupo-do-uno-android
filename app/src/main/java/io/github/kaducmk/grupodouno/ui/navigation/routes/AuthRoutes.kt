package io.github.kaducmk.grupodouno.ui.navigation.routes

import kotlinx.serialization.Serializable

@Serializable
sealed class AuthRoutes {
    @Serializable
    data object ToAuth : AuthRoutes()
}