package io.github.kaducmk.grupodouno.home.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.kaducmk.grupodouno.core.data.model.Usuario
import io.github.kaducmk.grupodouno.ui.theme.GrupoDoUnoTheme

@Composable
fun JogadorCard(modifier: Modifier = Modifier, usuario: Usuario) {
    Card(modifier = modifier) {
        ElevatedCard(modifier = Modifier.fillMaxWidth(0.5f)) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = usuario.displayName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )
        }
        Column(modifier = Modifier.height(64.dp)) {

        }
    }
}

@Preview(showSystemUi = true, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun JogadorCardPreview() {
    GrupoDoUnoTheme {
        JogadorCard(
            modifier = Modifier.padding(64.dp),
            usuario = Usuario("0", "Carlos", "", "")
        )
    }
}