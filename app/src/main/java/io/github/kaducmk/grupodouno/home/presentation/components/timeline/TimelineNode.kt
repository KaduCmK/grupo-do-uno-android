package io.github.kaducmk.grupodouno.home.presentation.components.timeline

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.kaducmk.grupodouno.home.data.LineParameters
import io.github.kaducmk.grupodouno.home.data.LineParametersDefaults
import io.github.kaducmk.grupodouno.ui.theme.GrupoDoUnoTheme

@Composable
fun TimelineNode(
    modifier: Modifier = Modifier,
    position: TimelineNodePosition,
    nodeColor: Color,
    lineParameters: LineParameters?,
    content: @Composable BoxScope.(modifier: Modifier) -> Unit
) {
    Box(
        modifier = modifier
            .wrapContentSize()
            .drawBehind {
                lineParameters?.let {
                    drawLine(
                        brush = lineParameters.brush,
                        start = Offset(x = 6.dp.toPx(), y = (6.dp.toPx())*2),
                        end = Offset(x = 6.dp.toPx(), y = this.size.height),
                        strokeWidth = lineParameters.strokeWidth.toPx()
                    )
                }
                drawCircle(
                    color = nodeColor,
                    radius = 6.dp.toPx(),
                    center = Offset(6.dp.toPx(), 6.dp.toPx())
                )
            }
    ) {
        content(
            Modifier.padding(
                start = 16.dp,
                bottom = if (position != TimelineNodePosition.LAST) 16.dp else 0.dp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TimelineNodePreview() {
    GrupoDoUnoTheme {
        Column(modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(16.dp)) {
            TimelineNode(
                position = TimelineNodePosition.FIRST,
                nodeColor = Color.Magenta,
                lineParameters = LineParametersDefaults.linearGradient(
                    startColor = MaterialTheme.colorScheme.primary,
                    endColor = MaterialTheme.colorScheme.error
                )
            ) { modifier -> Text(modifier = modifier, text = "ABC") }
            TimelineNode(
                position =  TimelineNodePosition.MIDDLE,
                nodeColor = Color.Magenta,
                lineParameters = LineParametersDefaults.linearGradient(
                    startColor = MaterialTheme.colorScheme.primary,
                    endColor = MaterialTheme.colorScheme.secondary
                )
            ) { modifier -> Text(modifier = modifier, text = "ABC") }
            TimelineNode(
                position = TimelineNodePosition.LAST,
                nodeColor = Color.Magenta,
                lineParameters = null
            ) { modifier -> Text(modifier = modifier, text = "ABC") }
        }
    }
}