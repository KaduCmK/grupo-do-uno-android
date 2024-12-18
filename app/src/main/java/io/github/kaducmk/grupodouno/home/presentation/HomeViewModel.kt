package io.github.kaducmk.grupodouno.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.kaducmk.grupodouno.core.data.di.AuthService
import io.github.kaducmk.grupodouno.core.data.di.FirestoreService
import io.github.kaducmk.grupodouno.home.data.HomeUiEvent
import io.github.kaducmk.grupodouno.home.data.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val firestoreService: FirestoreService,
    private val authService: AuthService
) : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        if (authService.getSignedInUser() == null) {
            viewModelScope.launch {
                _uiState.emit(HomeUiState.Unauthenticated)
            }
        }
    }

    fun uiEvent(event: HomeUiEvent) {
        viewModelScope.launch {
            when (event) {
                is HomeUiEvent.OnLogin -> {
                    _uiState.value = HomeUiState.Loading
                    authService.googleSignIn(event.context)
                        .onSuccess {
                            _uiState.emit(HomeUiState.Success(firestoreService.getUsuarios()))
                        }
                        .onFailure {
                            _uiState.emit(HomeUiState.Error(it.message ?: "Erro desconhecido"))
                        }
                }
                is HomeUiEvent.OnGetData -> {
                    _uiState.value = HomeUiState.Loading
                    _uiState.emit(HomeUiState.Success(firestoreService.getUsuarios()))
                }

                is HomeUiEvent.OnRemoveVitoria -> {
                    _uiState.value = HomeUiState.Loading
                    firestoreService.removeVitoria(event.usuario, event.vitoriaId)
                    _uiState.emit(HomeUiState.Success(firestoreService.getUsuarios()))
                }

                is HomeUiEvent.OnAddVitoria -> {
                    _uiState.value = HomeUiState.Loading
                    firestoreService.addVitoria(event.usuario)
                    _uiState.emit(HomeUiState.Success(firestoreService.getUsuarios()))
                }
            }
        }
    }
}