package com.rjwalker.within.datastore

import androidx.datastore.core.DataStore
import com.rjwalker.within.data.model.DarkThemeConfig
import com.rjwalker.within.data.model.UserData
import com.rjwalker.within.utils.TimeUtils.localDateToIsoString
import com.rjwalker.within.utils.TimeUtils.stringToLocalDate
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDate
import javax.inject.Inject

class WithinPreferencesDataSource @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>
) {
    val userData = userPreferences.data
        .map {
            UserData(
                name = it.userName,
                birthday = stringToLocalDate(it.birthday),
                showOnboarding = it.showOnboarding,
                darkThemeConfig = when (it.darkThemeConfig) {
                    null,
                    DarkThemeConfigProto.DARK_THEME_CONFIG_UNSPECIFIED,
                    DarkThemeConfigProto.DARK_THEME_CONFIG_FOLLOW_SYSTEM,
                    DarkThemeConfigProto.UNRECOGNIZED
                    -> DarkThemeConfig.FOLLOW_SYSTEM
                    DarkThemeConfigProto.DARK_THEME_CONFIG_LIGHT -> DarkThemeConfig.LIGHT
                    DarkThemeConfigProto.DARK_THEME_CONFIG_DARK -> DarkThemeConfig.DARK
                }
            )
        }

    suspend fun setUserName(userName: String) {
        userPreferences.updateData {
            it.copy { this.userName = userName }
        }
    }

    suspend fun setBirthday(birthday: LocalDate) {
        userPreferences.updateData {
            it.copy { this.birthday = localDateToIsoString(birthday) }
        }
    }

    suspend fun setShowOnboarding(showOnboarding: Boolean) {
        userPreferences.updateData {
            it.copy { this.showOnboarding = showOnboarding }
        }
    }

    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        userPreferences.updateData {
            it.copy {
                this.darkThemeConfig = when (darkThemeConfig) {
                    DarkThemeConfig.FOLLOW_SYSTEM ->
                        DarkThemeConfigProto.DARK_THEME_CONFIG_FOLLOW_SYSTEM
                    DarkThemeConfig.LIGHT -> DarkThemeConfigProto.DARK_THEME_CONFIG_LIGHT
                    DarkThemeConfig.DARK -> DarkThemeConfigProto.DARK_THEME_CONFIG_DARK
                }
            }
        }
    }
}