package com.rjwalker.within.data.repository

import com.rjwalker.within.data.model.DarkThemeConfig
import com.rjwalker.within.data.model.UserData
import com.rjwalker.within.datastore.WithinPreferencesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate
import javax.inject.Inject

internal class DefaultUserDataRepository @Inject constructor(
    private val withinPreferencesDataSource: WithinPreferencesDataSource
): UserDataRepository{
    override val userData: Flow<UserData> =
        withinPreferencesDataSource.userData

    override suspend fun setUserName(name: String) =
        withinPreferencesDataSource.setUserName(name)

    override suspend fun setBirthday(birthday: LocalDate) =
        withinPreferencesDataSource.setBirthday(birthday)

    override suspend fun setShowOnboarding(showOnboarding: Boolean) =
        withinPreferencesDataSource.setShowOnboarding(showOnboarding)

    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) =
        withinPreferencesDataSource.setDarkThemeConfig(darkThemeConfig)



}