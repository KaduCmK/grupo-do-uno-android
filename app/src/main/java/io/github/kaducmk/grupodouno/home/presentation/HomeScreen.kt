package io.github.kaducmk.grupodouno.home.presentation

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import io.github.kaducmk.grupodouno.home.presentation.components.JogadorCard
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

    Surface {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
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
                    Text(text = "Fulano", style = MaterialTheme.typography.titleLarge)
                }
            }
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
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(items = uiState.data, key = { it.uid!! }) { usuario ->
                            JogadorCard(usuario = usuario, uiEvent = uiEvent)
                        }
                    }
                    Spacer(Modifier.height(16.dp))
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
            uiState = HomeUiState.Unauthenticated,
            uiEvent = {})
    }
}