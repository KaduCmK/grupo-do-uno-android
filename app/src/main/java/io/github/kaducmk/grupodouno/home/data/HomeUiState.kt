package io.github.kaducmk.grupodouno.home.data

sealed class HomeUiState {
    data object Loading : HomeUiState()
    data class Success(val data: String) : HomeUiState()
    data class Error(val error: String) : HomeUiState()
}