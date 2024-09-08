package com.rjwalker.within.datastore

import androidx.datastore.core.DataStore
import com.rjwalker.within.data.model.UserData
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WithinPreferencesDataSource @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>
) {
    val userData = userPreferences.data
        .map {
            UserData(
                showOnboarding = it.showOnboarding
            )
        }

    suspend fun setShowOnboarding(showOnboarding: Boolean) {
        userPreferences.updateData {
            it.toBuilder().setShowOnboarding(showOnboarding).build()
        }
    }
}