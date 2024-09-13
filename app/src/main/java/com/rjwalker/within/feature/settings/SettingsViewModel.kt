package com.rjwalker.within.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rjwalker.within.data.repository.UserDataRepository
import com.rjwalker.within.data.model.DarkThemeConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository
) : ViewModel() {
    val settingsUiState: StateFlow<SettingsUiState> =
        userDataRepository.userData
            .map { userData ->
                SettingsUiState.Success(
                    settings = UserSettings(
                        showOnboarding = userData.showOnboarding,
                        darkThemeConfig = userData.darkThemeConfig
                    )
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = SettingsUiState.Loading
            )

    fun updateShowOnboarding(showOnboarding: Boolean) {
        viewModelScope.launch {
            userDataRepository.setShowOnboarding(showOnboarding)
        }
    }

    fun updateDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        viewModelScope.launch {
            userDataRepository.setDarkThemeConfig(darkThemeConfig)
        }
    }
}

data class UserSettings(
    val showOnboarding: Boolean,
    val darkThemeConfig: DarkThemeConfig
)

sealed interface SettingsUiState {
    data object Loading : SettingsUiState
    data class Success(val settings: UserSettings) : SettingsUiState
}