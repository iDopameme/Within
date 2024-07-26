package com.rjwalker.within.datastore

import androidx.datastore.core.Serializer
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class UserPreferencesSerializer @Inject constructor() : Serializer<UserPreferences> {
    override val defaultValue: UserPreferences = UserPreferences.getDefaultInstance()
    override suspend fun readFrom(input: InputStream): UserPreferences {
        TODO("Not yet implemented")
    }

    override suspend fun writeTo(t: UserPreferences, output: OutputStream) {
        TODO("Not yet implemented")
    }
}