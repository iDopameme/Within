package com.rjwalker.within.data.di

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

}