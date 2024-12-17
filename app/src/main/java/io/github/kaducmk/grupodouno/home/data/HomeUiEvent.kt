package io.github.kaducmk.grupodouno.home.data

sealed interface HomeUiEvent {
    data object OnGetData : HomeUiEvent
}