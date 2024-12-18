package io.github.kaducmk.grupodouno.home.data

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object LineParametersDefaults {
    val defaultStrokeWidth = 3.dp

    fun linearGradient(
        strokeWidth: Dp = defaultStrokeWidth,
        startColor: Color,
        endColor: Color,
        startY: Float = 0f,
        endY: Float = Float.POSITIVE_INFINITY
    ): LineParameters {
        val brush = Brush.verticalGradient(
            colors = listOf(startColor, endColor),
            startY = startY,
            endY = endY
        )
        return LineParameters(strokeWidth, brush)
    }
}