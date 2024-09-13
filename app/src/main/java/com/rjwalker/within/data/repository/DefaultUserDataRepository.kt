package com.rjwalker.within.data.repository

import com.rjwalker.within.data.model.DarkThemeConfig
import com.rjwalker.within.data.model.UserData
import com.rjwalker.within.datastore.WithinPreferencesDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class DefaultUserDataRepository @Inject constructor(
    private val withinPreferencesDataSource: WithinPreferencesDataSource
): UserDataRepository{
    override val userData: Flow<UserData> =
        withinPreferencesDataSource.userData

    override suspend fun setShowOnboarding(showOnboarding: Boolean) =
        withinPreferencesDataSource.setShowOnboarding(showOnboarding)

    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) =
        withinPreferencesDataSource.setDarkThemeConfig(darkThemeConfig)



}