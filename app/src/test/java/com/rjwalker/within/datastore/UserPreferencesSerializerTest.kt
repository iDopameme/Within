package com.rjwalker.within.datastore

import androidx.datastore.core.CorruptionException
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import kotlin.test.assertEquals


class UserPreferencesSerializerTest {
    private val userPreferencesSerializer = UserPreferencesSerializer()

    @Test
    fun testReadFrom_validProto() = runTest {
        val preferences = UserPreferences.newBuilder().setShowOnboarding(true).build()
        val inputStream = ByteArrayInputStream(preferences.toByteArray())

        val result = userPreferencesSerializer.readFrom(inputStream)

        assertEquals(preferences, result)
    }

    @Test(expected = CorruptionException::class)
    fun testReadFrom_invalidProto() = runTest {
        val inputStream = ByteArrayInputStream("invalid data".toByteArray())
        userPreferencesSerializer.readFrom(inputStream)
    }

    @Test
    fun testWriteTo() = runTest {
        val preferences = UserPreferences.newBuilder().setShowOnboarding(false).build()
        val outputStream = ByteArrayOutputStream()

        userPreferencesSerializer.writeTo(preferences, outputStream)

        val result = UserPreferences.parseFrom(outputStream.toByteArray())
        assertEquals(preferences, result)
    }
}