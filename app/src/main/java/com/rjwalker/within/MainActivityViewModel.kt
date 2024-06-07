package com.rjwalker.within

import androidx.lifecycle.ViewModel
import com.rjwalker.within.data.model.UserData

class MainActivityViewModel : ViewModel() {

}

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data class Success(val userData: UserData) : MainActivityUiState
}