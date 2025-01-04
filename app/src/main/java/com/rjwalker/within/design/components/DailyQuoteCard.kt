package com.rjwalker.within.design.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rjwalker.within.utils.TimeUtils.getLocalDateTimeInTimeZone
import kotlinx.datetime.TimeZone

@Composable
fun DailyQuoteCard(
    quote: String,
    author: String,
    modifier: Modifier = Modifier
) {
    val dayOfWeek = getLocalDateTimeInTimeZone(TimeZone.currentSystemDefault()).dayOfWeek.name

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
    ) {
        Text(
            text = dayOfWeek,
            modifier = Modifier
                .padding(8.dp),
            textAlign = TextAlign.Start
        )
        Text(
            text = author,
            modifier = Modifier
                .padding(8.dp),
            textAlign = TextAlign.Start
        )
        Text(
            text = quote,
            modifier = Modifier
                .padding(8.dp),
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun DailyQuoteCardLoading(
    modifier: Modifier = Modifier,
    widthOfShadowBrush: Int = 500,
    angleOfAxisY: Float = 270f,
    durationMillis: Int = 1000,
) {
    val shimmerColors = listOf(
        Color.White.copy(alpha = 0.3f),
        Color.White.copy(alpha = 0.5f),
        Color.White.copy(alpha = 1.0f),
        Color.White.copy(alpha = 0.5f),
        Color.White.copy(alpha = 0.3f)
    )

    val transition = rememberInfiniteTransition(label = "")
    val transitionAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = (durationMillis + widthOfShadowBrush).toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ), label = "Shimmer loading animation"
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(x = transitionAnim.value - widthOfShadowBrush, y = 0f),
        end = Offset(x = transitionAnim.value, y = angleOfAxisY)
    )

    Box(
        modifier = modifier
    ) {
        Spacer(
            modifier = Modifier
                .matchParentSize()
                .background(brush)
        )
    }
}


@Preview(name = "Daily Quote Tip")
@Composable
fun DailyQuoteCardPreview() {
    DailyQuoteCard(
        quote = "Hello, world!",
        author = "John Doe",
        modifier = Modifier
    )
}

@Preview(name = "Daily Quote Tip Loading")
@Composable
fun ShimmerLoadingPreview() {
    DailyQuoteCardLoading(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .background(Color.LightGray)
    )
}

