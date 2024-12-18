package io.github.kaducmk.grupodouno.home.data

import io.github.kaducmk.grupodouno.core.data.model.Usuario

sealed class HomeUiState {
    data object Unauthenticated : HomeUiState()
    data object Loading : HomeUiState()
    data class Success(val data: List<Usuario>) : HomeUiState()
    data class Error(val error: String) : HomeUiState()
}