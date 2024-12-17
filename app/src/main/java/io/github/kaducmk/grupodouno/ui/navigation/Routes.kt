package io.github.kaducmk.grupodouno.ui.navigation

sealed class Routes {
    sealed class AuthRoutes {
        data object ToAuth : Routes()
    }

    sealed class HomeRoutes {
        data object ToHome : Routes()
    }
}