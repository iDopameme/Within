package com.rjwalker.within.data.repository

import com.rjwalker.within.data.model.DarkThemeConfig
import com.rjwalker.within.data.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    val userData: Flow<UserData>

    suspend fun setShowOnboarding(showOnboarding: Boolean)

    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)
}