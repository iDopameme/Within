package com.rjwalker.within.firebase

import com.google.firebase.auth.FirebaseAuth
import com.rjwalker.within.data.repository.LoginSignupRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {
    @Provides
    @Singleton
    fun providesFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun providesAuthenticationRepository(auth: FirebaseAuth): LoginSignupRepository {
        return LoginSignupRepository(auth)
    }
}