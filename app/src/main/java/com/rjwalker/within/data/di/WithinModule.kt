package com.rjwalker.within.data.di

import com.rjwalker.within.data.repository.AgendaRepository
import com.rjwalker.within.data.repository.DefaultUserDataRepository
import com.rjwalker.within.data.repository.HomeAgendaRepository
import com.rjwalker.within.data.repository.HomeQuoteRepository
import com.rjwalker.within.data.repository.QuoteRepository
import com.rjwalker.within.data.repository.UserDataRepository
import com.rjwalker.within.data.util.ConnectivityManagerNetworkMonitor
import com.rjwalker.within.data.util.NetworkMonitor
import com.rjwalker.within.data.util.TimeZoneBroadcastMonitor
import com.rjwalker.within.data.util.TimeZoneMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class WithinModule {
    @Binds
    internal abstract fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor

    @Binds
    internal abstract fun binds(impl: TimeZoneBroadcastMonitor): TimeZoneMonitor

    @Binds
    internal abstract fun bindsAgendaRepository(
        agendaRepository: HomeAgendaRepository
    ): AgendaRepository

    @Binds
    internal abstract fun bindsUserDataRepository(
        userDataRepository: DefaultUserDataRepository
    ): UserDataRepository

    @Binds
    internal abstract fun bindsQuotesRepository(
        quoteRepository: HomeQuoteRepository
    ): QuoteRepository
}