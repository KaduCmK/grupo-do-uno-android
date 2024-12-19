package io.github.kaducmk.grupodouno.home.presentation.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.kaducmk.grupodouno.core.data.model.Vitoria
import io.github.kaducmk.grupodouno.home.data.LineParametersDefaults
import io.github.kaducmk.grupodouno.home.presentation.components.timeline.TimelineNode
import io.github.kaducmk.grupodouno.home.presentation.components.timeline.TimelineNodePosition
import java.time.format.DateTimeFormatter

@Composable
fun Historico(modifier: Modifier = Modifier, historico: List<Pair<Vitoria, String?>>) {
    LazyColumn {
        itemsIndexed(
            items = historico,
            key = { index, (vitoria, jogador) -> "$index-$vitoria-$jogador" }
        ) { index, (vitoria, jogador) ->
            TimelineNode(
                position = when (index) {
                    0 -> TimelineNodePosition.FIRST
                    historico.size - 1 -> TimelineNodePosition.LAST
                    else -> TimelineNodePosition.MIDDLE
                },
                nodeColor = MaterialTheme.colorScheme.primary,
                lineParameters = LineParametersDefaults.linearGradient(
                    startColor = MaterialTheme.colorScheme.primary,
                    endColor = MaterialTheme.colorScheme.secondary
                )
            ) { timelineModifier ->
                Text(
                    modifier = timelineModifier,
                    text = "$jogador | ${vitoria.data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))}",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Preview
@Composable
private fun HistoricoPreview() {

}