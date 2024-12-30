package com.rjwalker.within.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rjwalker.within.data.model.Quote
import com.rjwalker.within.data.repository.QuoteRepository
import com.rjwalker.within.data.repository.UserDataRepository
import com.rjwalker.within.data.states.QuoteUiState
import com.rjwalker.within.database.model.asExternalModel
import com.rjwalker.within.network.WithinNetworkDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    userDataRepository: UserDataRepository,
    private val quoteRepository: QuoteRepository,
) : ViewModel() {

    private val _shouldShowOnboarding: Flow<Boolean> =
        userDataRepository.userData.map { !it.showOnboarding }
    val showShowOnboarding: StateFlow<Boolean> =
        _shouldShowOnboarding.map { it }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )

    private val _randomQuote = MutableStateFlow<QuoteUiState>(QuoteUiState.Empty)
    val randomQuote: StateFlow<QuoteUiState> = _randomQuote.asStateFlow()

    init {
        fetchRandomQuote()
    }

    private fun fetchRandomQuote() {
        _randomQuote.value = QuoteUiState.Loading
        Log.d(TAG, "Quote Loading...")

        viewModelScope.launch {
            try {
                quoteRepository.getRandomQuote()
                    .collect { quote ->
                        if (quote.quote.isBlank()) {
                            _randomQuote.value = QuoteUiState.Empty
                            Log.d(TAG, "Quote Empty")
                        } else {
                            _randomQuote.value = QuoteUiState.Success(quote)
                            Log.d(TAG, "Quote Success")
                        }
                    }
            } catch (e: Exception) {
                _randomQuote.value = QuoteUiState.Error(e.message)
                Log.d(TAG, "Quote Error ${e.message}")
            }
        }
    }

    companion object {
        private const val TAG = "HomeScreenViewModel"
    }
}