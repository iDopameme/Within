package com.rjwalker.within.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.rjwalker.within.network.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LoginSignupRepository @Inject constructor(
    val auth: FirebaseAuth
) {

    suspend fun signUpWithEmailAndPassword(email: String, password: String): Flow<Result<Unit>> = flow {
        try {
            auth.createUserWithEmailAndPassword(email, password).await()
            emit(Result.Success(Unit)) // Emit Result.Success(Unit) on successful signup
        } catch (e: Exception) {
            emit(Result.Error(e)) // Emit Result.Error on failure
        }
    }

    suspend fun loginWithEmailAndPassword(email: String, password: String): Flow<Result<Unit>> = flow {
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            emit(Result.Success(Unit)) // Emit Result.Success(Unit) on successful login
        } catch (e: Exception) {
            emit(Result.Error(e)) // Emit Result.Error on failure
        }
    }

    fun isUserAuthenticated(): Boolean = auth.currentUser != null


    companion object {
        private const val TAG = "LoginSignupRepository"
    }
}