package com.rjwalker.within.design.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rjwalker.within.ui.DevicePreviews
import com.rjwalker.within.utils.TimeUtils
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
            .size(width = 240.dp, height = 100.dp)
    ) {
        Text(
            text = dayOfWeek,
            modifier = Modifier
                .padding(8.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = author,
            modifier = Modifier
                .padding(8.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = quote,
            modifier = Modifier
                .padding(8.dp),
            textAlign = TextAlign.Center
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

