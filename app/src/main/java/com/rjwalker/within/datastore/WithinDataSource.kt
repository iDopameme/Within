package com.rjwalker.within.datastore

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WithinDataSource @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>
) {
    val userData = userPreferences.data
        .map {

        }
}