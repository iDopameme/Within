package com.rjwalker.within.datastore

import androidx.datastore.core.DataStore
import com.rjwalker.within.datastoreTest.testUserPreferencesDataStore
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

//@OptIn(ExperimentalCoroutinesApi::class)
//class WithinDataStoreTest {
//
//    private val testScope = TestScope(UnconfinedTestDispatcher())
//    private lateinit var subject: WithinPreferencesDataSource
//
//    @get:Rule
//    val tmpFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()
//
//
//    @Before
//    fun setup() {
//        subject = WithinPreferencesDataSource(
//            tmpFolder.testUserPreferencesDataStore(testScope)
//        )
//    }
//
//    @Test
//    fun `showOnboarding is false by default`() = testScope.runTest {
//        assertFalse(subject.userData.first().showOnboarding)
//    }
//
//    @Test
//    fun `showOnboarding is true after setting it to true`() = testScope.runTest {
//        // Update showOnboarding to true
//        subject.setShowOnboarding(true)
//
//        // Verify that showOnboarding is now true
//        assertTrue(subject.userData.first().showOnboarding)
//    }
//}