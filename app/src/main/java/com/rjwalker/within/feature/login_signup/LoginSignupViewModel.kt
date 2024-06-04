package com.rjwalker.within.feature.login_signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rjwalker.within.data.repository.LoginSignupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginSignupViewModel @Inject constructor(
    private val authRepository: LoginSignupRepository
) : ViewModel() {
    val email = MutableStateFlow("")
    val password = MutableStateFlow("")
    val errorMessage = MutableStateFlow<String?>(null)

    val isAuthenticated = MutableStateFlow(false)

    fun login() {
        viewModelScope.launch {
            val result = authRepository.loginWithEmailAndPassword(
                email.value, password.value
            )
        }
    }

    fun signup() {
        viewModelScope.launch {

        }
    }
}