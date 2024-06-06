package com.rjwalker.within.feature.login_signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.rjwalker.within.data.repository.LoginSignupRepository
import com.rjwalker.within.network.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginSignupViewModel @Inject constructor(
    private val authRepository: LoginSignupRepository
) : ViewModel() {

    // StateFlow to hold the latest Result
    val authResult = MutableStateFlow<Result<Unit>>(Result.Loading)

    private val isAuthenticated = MutableStateFlow(authRepository.isUserAuthenticated())

    private val authStateListener = FirebaseAuth.AuthStateListener { auth ->
        viewModelScope.launch {
            isAuthenticated.value = auth.currentUser != null
        }
    }

    init {
        authRepository.auth.addAuthStateListener(authStateListener)
    }

    override fun onCleared() {
        super.onCleared()
        authRepository.auth.removeAuthStateListener(authStateListener)
    }

    fun signup(email: String, password: String) = viewModelScope.launch {
        authRepository.signUpWithEmailAndPassword(email, password).collect {
            authResult.value = it
        }
    }

    fun login(email: String, password: String) = viewModelScope.launch {
        authRepository.loginWithEmailAndPassword(email, password).collect {
            authResult.value = it
        }
    }
}
