package io.github.kaducmk.grupodouno.home.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.kaducmk.grupodouno.core.data.model.Vitoria
import io.github.kaducmk.grupodouno.home.data.HomeUiState
import io.github.kaducmk.grupodouno.ui.theme.GrupoDoUnoTheme

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    historico: List<Pair<Vitoria, String?>>
) {
    val lastWinner = historico.maxByOrNull { it.first.data }?.second

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, start = 8.dp, end = 8.dp),
        ) {
            Text(
                text = "Uno",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.SemiBold
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, top = 8.dp, bottom = 24.dp)
        ) {
            Text(
                text = "Último vencedor: ",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )
            AnimatedVisibility(uiState is HomeUiState.Success) {
                Text(text = lastWinner ?: "", style = MaterialTheme.typography.titleLarge)
            }
        }
    }
}

@Preview
@Composable
private fun TopBarPreview() {
    GrupoDoUnoTheme {
        TopBar(uiState = HomeUiState.Success(emptyList()), historico = emptyList())
    }
}