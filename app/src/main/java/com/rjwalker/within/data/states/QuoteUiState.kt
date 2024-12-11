package com.rjwalker.within.data.states

import com.rjwalker.within.data.model.Quote

sealed interface QuoteUiState {
    data class Success(val quote: Quote) : QuoteUiState
    data class Error(val message: String?) : QuoteUiState
    data object Loading : QuoteUiState
    data object Empty : QuoteUiState
}