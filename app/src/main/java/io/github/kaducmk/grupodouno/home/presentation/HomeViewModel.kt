package io.github.kaducmk.grupodouno.home.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.kaducmk.grupodouno.home.data.HomeUiEvent
import io.github.kaducmk.grupodouno.home.data.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun uiEvent(event: HomeUiEvent) {
        when (event) {
            HomeUiEvent.OnGetData -> {
                _uiState.value = HomeUiState.Success("Data")
            }
        }
    }
}