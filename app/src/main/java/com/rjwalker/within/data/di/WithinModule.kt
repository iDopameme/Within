package com.rjwalker.within.data.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.rjwalker.within.data.repository.LoginSignupRepository
import com.rjwalker.within.data.util.ConnectivityManagerNetworkMonitor
import com.rjwalker.within.data.util.NetworkMonitor
import com.rjwalker.within.network.Dispatcher
import com.rjwalker.within.network.WithinDispatchers.IO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WithinModule {
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

    @Provides
    @Singleton
    fun providesNetworkMonitor(
        @ApplicationContext context: Context,
        @Dispatcher(IO) ioDispatcher: CoroutineDispatcher
    ): NetworkMonitor = ConnectivityManagerNetworkMonitor(context, ioDispatcher)

}