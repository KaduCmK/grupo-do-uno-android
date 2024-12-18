package io.github.kaducmk.grupodouno.home.data

import android.content.Context
import io.github.kaducmk.grupodouno.core.data.model.Usuario

sealed interface HomeUiEvent {
    data object OnGetData : HomeUiEvent
    data class OnAddVitoria(val usuario: Usuario) : HomeUiEvent
    data class OnRemoveVitoria(val usuario: Usuario, val vitoriaId: String) : HomeUiEvent
    data class OnLogin(val context: Context) : HomeUiEvent
}