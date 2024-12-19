package io.github.kaducmk.grupodouno.home.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import io.github.kaducmk.grupodouno.R
import io.github.kaducmk.grupodouno.core.data.model.Usuario
import io.github.kaducmk.grupodouno.home.data.HomeUiEvent
import io.github.kaducmk.grupodouno.home.data.HomeUiState
import io.github.kaducmk.grupodouno.home.presentation.components.Historico
import io.github.kaducmk.grupodouno.home.presentation.components.JogadoresPager
import io.github.kaducmk.grupodouno.home.presentation.components.TopBar
import io.github.kaducmk.grupodouno.ui.theme.GrupoDoUnoTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    uiState: HomeUiState,
    uiEvent: (HomeUiEvent) -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        if (uiState !is HomeUiState.Unauthenticated) {
            uiEvent(HomeUiEvent.OnGetData)
        }
    }
    val vitoriasMap = (uiState as? HomeUiState.Success)?.data
        ?.flatMap { usuario ->
            // vai iterar a lista de vitorias dentro da lista de usuarios e mapear a vitoria para
            // o nome do usuario
            usuario.vitorias.map { it to usuario.displayName }
        }
        ?.sortedByDescending { it.first.data }

    Surface {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            TopBar(uiState = uiState, historico = vitoriasMap ?: emptyList())
            when (uiState) {
                is HomeUiState.Unauthenticated -> {
                    Button({ uiEvent(HomeUiEvent.OnLogin(context)) }) {
                        Icon(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(R.drawable.google_icon_logo_svgrepo_com),
                            tint = MaterialTheme.colorScheme.surface,
                            contentDescription = null
                        )
                        Spacer(Modifier.width(8.dp))
                        Text("Login")
                    }
                }

                is HomeUiState.Loading -> {
                    CircularProgressIndicator()
                }

                is HomeUiState.Success -> {
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null
                        )
                        Text(
                            "Jogadores",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    JogadoresPager(uiState = uiState, uiEvent = uiEvent)
                    Spacer(Modifier.height(8.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Icon(
                            imageVector = Icons.Default.History,
                            contentDescription = null
                        )
                        Text(
                            "Histórico",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Historico(historico = vitoriasMap ?: emptyList())
                }

                is HomeUiState.Error -> {
                    Text(
                        text = "Erro: ${uiState.error}",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    GrupoDoUnoTheme {
        HomeScreen(
            navController = rememberNavController(),
            uiState = HomeUiState.Success(
                data = listOf(
                    Usuario(
                        uid = "1",
                        displayName = "Fulano",
                        email = ""
                    ),
                    Usuario(
                        uid = "2",
                        displayName = "Ciclano",
                        email = ""
                    ),
                    Usuario(
                        uid = "3",
                        displayName = "Beltrano",
                        email = ""
                    )
                )
            ),
            uiEvent = {})
    }
}