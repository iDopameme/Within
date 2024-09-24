package com.rjwalker.within.feature.home

sealed interface OnboardingUiState {
    data object Loading : OnboardingUiState
    data object NotCompleted : OnboardingUiState
    data object Completed : OnboardingUiState

}