package com.rjwalker.within.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginSignupRepository @Inject constructor(
    private val auth: FirebaseAuth
) {
    suspend fun loginWithEmailAndPassword(email: String, password: String): Result<Unit> =
        withContext(Dispatchers.IO) {
            try {
                auth.signInWithEmailAndPassword(email, password).await()
                Result.success(Unit)
            } catch (e: Exception) {
                Log.d(TAG, "loginWithEmailAndPassword:failure ${e.message}")
                Result.failure(e)
            }
        }

    suspend fun signUpWithEmailAndPassword(email: String, password: String): Result<Unit> =
        withContext(Dispatchers.IO) {
            try {
                auth.createUserWithEmailAndPassword(email, password).await()
                Result.success(Unit)
            } catch (e: Exception) {
                Log.d(TAG, "signUpWithEmailAndPassword:failure ${e.message}")
                Result.failure(e)
            }
        }

    fun isUserAuthenticated(): Boolean = auth.currentUser != null


    companion object {
        private const val TAG = "LoginSignupRepository"
    }
}