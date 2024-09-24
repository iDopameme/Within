package com.rjwalker.within.data.model

import kotlinx.datetime.LocalDate

data class UserData(
    val name: String,
    val birthday: LocalDate,
    val showOnboarding: Boolean,
    val darkThemeConfig: DarkThemeConfig
)
