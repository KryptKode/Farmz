package com.kryptkode.farmz.app.data.auth

import com.google.common.truth.Truth.assertThat
import com.kryptkode.farmz.app.data.keyvaluestore.KeyValueStore
import com.kryptkode.farmz.app.data.state.DataState
import com.kryptkode.farmz.app.dispatcher.AppDispatchers
import com.kryptkode.farmz.app.domain.AuthRepository
import com.kryptkode.farmz.base.MainCoroutineRule
import com.kryptkode.farmz.base.runBlockingTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AuthRepositoryTest {

    // Overrides Dispatchers.Main used in Coroutines
    @get:Rule
    var coroutineRule = MainCoroutineRule()


    private lateinit var authRepository: AuthRepository
    private val appDispatchers: AppDispatchers = mockk()
    private val keyValueStore: KeyValueStore = mockk()

    @Before
    fun before() {
        stubAppDispatchers()
        stubKeyValueStore()
        authRepository = AuthRepositoryImpl(appDispatchers, keyValueStore)
    }

    @Test
    fun `login with correct details succeeds`() = coroutineRule.runBlockingTest {

        val testEmail = AuthRepositoryImpl.testLoginDetails.keys.first()
        val testPassword = AuthRepositoryImpl.testLoginDetails.values.first()

        val result = authRepository.login(testEmail, testPassword)

        assertThat(result).isInstanceOf(DataState.Success::class.java)
    }

    @Test
    fun `login with correct details sets keystore login to true`() = coroutineRule.runBlockingTest {

        val testEmail = AuthRepositoryImpl.testLoginDetails.keys.first()
        val testPassword = AuthRepositoryImpl.testLoginDetails.values.first()

        authRepository.login(testEmail, testPassword)

        coVerify { keyValueStore.setLoggedIn(true) }
    }

    @Test
    fun `login with incorrect email fails`() = coroutineRule.runBlockingTest {

        val testEmail = ""
        val testPassword = AuthRepositoryImpl.testLoginDetails.values.first()

        val result = authRepository.login(testEmail, testPassword)

        assertThat(result).isInstanceOf(DataState.Error::class.java)
    }

    @Test
    fun `login with incorrect password fails`() = coroutineRule.runBlockingTest {

        val testEmail = AuthRepositoryImpl.testLoginDetails.keys.first()
        val testPassword = ""

        val result = authRepository.login(testEmail, testPassword)

        assertThat(result).isInstanceOf(DataState.Error::class.java)
    }


    private fun stubKeyValueStore() {
        coEvery { keyValueStore.setLoggedIn(any()) } returns Unit
    }

    private fun stubAppDispatchers() {
        every {
            appDispatchers.io()
        } returns coroutineRule.testDispatcher
    }
}