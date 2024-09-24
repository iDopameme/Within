package com.rjwalker.within.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rjwalker.within.data.model.Quote
import com.rjwalker.within.data.repository.QuoteRepository
import com.rjwalker.within.data.repository.UserDataRepository
import com.rjwalker.within.database.model.asExternalModel
import com.rjwalker.within.network.WithinNetworkDataSource
import com.rjwalker.within.network.model.NetworkQuote
import com.rjwalker.within.network.model.asEntity
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
    private val userDataRepository: UserDataRepository,
    private val quoteRepository: QuoteRepository,
    private val network: WithinNetworkDataSource,
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

    fun fetchRandomQuote() {
        viewModelScope.launch {
            _randomQuote.value = QuoteUiState.Loading

            try {
                val networkQuote = network.getRandomQuote()
                _randomQuote.value = QuoteUiState.Success(networkQuote.asEntity().asExternalModel())
            } catch (e: Exception) {
                _randomQuote.value = QuoteUiState.Error
            }
        }

    }
}

sealed interface QuoteUiState {
    data class Success(val quote: Quote) : QuoteUiState
    data object Error : QuoteUiState
    data object Loading : QuoteUiState
    data object Empty : QuoteUiState
}