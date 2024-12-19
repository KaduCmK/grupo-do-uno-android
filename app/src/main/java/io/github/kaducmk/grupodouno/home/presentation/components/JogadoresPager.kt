package io.github.kaducmk.grupodouno.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowLeft
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.kaducmk.grupodouno.home.data.HomeUiEvent
import io.github.kaducmk.grupodouno.home.data.HomeUiState
import io.github.kaducmk.grupodouno.ui.theme.GrupoDoUnoTheme
import kotlinx.coroutines.launch

@Composable
fun JogadoresPager(modifier: Modifier = Modifier, uiState: HomeUiState.Success, uiEvent: (HomeUiEvent) -> Unit) {
    val pagerState = rememberPagerState { uiState.data.size }
    val coroutineScope = rememberCoroutineScope()

    HorizontalPager(
        modifier = modifier,
        state = pagerState,
    ) {
        JogadorCard(
            modifier = Modifier.padding(horizontal = 32.dp),
            usuario = uiState.data[it],
            uiEvent = uiEvent
        )
    }
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = Modifier.padding(horizontal = 8.dp),
            onClick = {
                coroutineScope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1) }
            },
            enabled = pagerState.currentPage > 0
        ) {
            Icon(
                modifier = Modifier.size(48.dp),
                imageVector = Icons.AutoMirrored.Default.ArrowLeft,
                contentDescription = null
            )
        }
        repeat(pagerState.pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration)
                MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.onBackground
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(8.dp)
            )
        }
        IconButton(
            modifier = Modifier.padding(horizontal = 8.dp),
            onClick = {
                coroutineScope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
            },
            enabled = pagerState.currentPage < pagerState.pageCount - 1
        ) {
            Icon(
                modifier = Modifier.size(48.dp),
                imageVector = Icons.AutoMirrored.Default.ArrowRight,
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
private fun JogadoresPagerPreview() {
    GrupoDoUnoTheme {
        JogadoresPager(uiState = HomeUiState.Success(emptyList()), uiEvent = {})
    }
}