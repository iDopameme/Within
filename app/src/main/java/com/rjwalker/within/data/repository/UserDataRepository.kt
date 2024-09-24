package com.rjwalker.within.data.repository

import com.rjwalker.within.data.model.DarkThemeConfig
import com.rjwalker.within.data.model.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

interface UserDataRepository {

    val userData: Flow<UserData>

    suspend fun setUserName(name: String)

    suspend fun setBirthday(birthday: LocalDate)

    suspend fun setShowOnboarding(showOnboarding: Boolean)

    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)
}