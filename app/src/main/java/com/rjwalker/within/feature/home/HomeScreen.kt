package com.rjwalker.within.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rjwalker.within.data.states.QuoteUiState
import com.rjwalker.within.design.components.DailyQuoteCard
import com.rjwalker.within.design.components.ThemePreviews

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {
    val quoteState = viewModel.randomQuote.collectAsStateWithLifecycle().value

    Column {
        when (quoteState) {
            is QuoteUiState.Loading -> {}
            is QuoteUiState.Success -> {
                DailyQuoteCard(quote = quoteState.quote.quote, author = quoteState.quote.author)
            }
            is QuoteUiState.Error -> {
                DailyQuoteCard(quote = quoteState.message.toString(), author = "")
            }
            is QuoteUiState.Empty -> {

            }
        }


    }
}



@ThemePreviews
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}