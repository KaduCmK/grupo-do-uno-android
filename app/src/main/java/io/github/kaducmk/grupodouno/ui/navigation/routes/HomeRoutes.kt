package io.github.kaducmk.grupodouno.ui.navigation.routes

import kotlinx.serialization.Serializable

@Serializable
sealed class HomeRoutes {
    @Serializable
    data object ToHome : HomeRoutes()
}