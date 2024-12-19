package io.github.kaducmk.grupodouno.home.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import io.github.kaducmk.grupodouno.core.data.model.Usuario
import io.github.kaducmk.grupodouno.core.data.model.Vitoria
import io.github.kaducmk.grupodouno.home.data.HomeUiEvent
import io.github.kaducmk.grupodouno.home.data.LineParametersDefaults
import io.github.kaducmk.grupodouno.home.presentation.components.timeline.TimelineNode
import io.github.kaducmk.grupodouno.home.presentation.components.timeline.TimelineNodePosition
import io.github.kaducmk.grupodouno.ui.theme.GrupoDoUnoTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun JogadorCard(modifier: Modifier = Modifier, usuario: Usuario, uiEvent: (HomeUiEvent) -> Unit) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(usuario.photoUrl)
            .size(Size.ORIGINAL)
            .crossfade(true)
            .build()
    )

    Card(modifier = modifier.fillMaxWidth()) {
        ElevatedCard {
            Row(
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = CircleShape
                        )
                ) {
                    Image(
                        modifier = Modifier.size(36.dp),
                        painter = painter,
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )
                    if (painter.state !is AsyncImagePainter.State.Success) {
                        Icon(
                            modifier = Modifier.size(36.dp),
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                        )

                    }
                }
                Text(
                    text = usuario.displayName ?: "Ue",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1
                )
                Text(
                    text = usuario.vitorias.size.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        OutlinedCard {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton({
                    uiEvent(
                        HomeUiEvent.OnRemoveVitoria(usuario, usuario.vitorias.first().id)
                    )
                }) {
                    Icon(imageVector = Icons.Default.Remove, contentDescription = null)
                }
                VerticalDivider(modifier = Modifier.height(32.dp))
                IconButton({
                    uiEvent(HomeUiEvent.OnAddVitoria(usuario))
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }
        }
        LazyColumn(
            modifier = Modifier
                .height(90.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(8.dp)
        ) {
            itemsIndexed(
                items = usuario.vitorias,
                key = { _, vitoria -> vitoria.id }) { index, vitoria ->
                TimelineNode(
                    position = when (index) {
                        0 -> TimelineNodePosition.FIRST
                        usuario.vitorias.size - 1 -> TimelineNodePosition.LAST
                        else -> TimelineNodePosition.MIDDLE
                    },
                    nodeColor = MaterialTheme.colorScheme.primary,
                    lineParameters = LineParametersDefaults.linearGradient(
                        startColor = MaterialTheme.colorScheme.primary,
                        endColor = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text(
                        modifier = it,
                        text = vitoria.data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview()
@Composable
private fun JogadorCardPreview() {
    GrupoDoUnoTheme {
        JogadorCard(
            modifier = Modifier.padding(horizontal = 32.dp),
            usuario = Usuario(
                "0", "Carlos", "", "", listOf(
                    Vitoria("1", LocalDateTime.now()),
                    Vitoria("2", LocalDateTime.now()),
                    Vitoria("3", LocalDateTime.now()),
                    Vitoria("4", LocalDateTime.now())
                )
            ),
            {}
        )
    }
}