package com.kryptkode.farmz.screens.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.kryptkode.farmz.app.data.state.DataState
import com.kryptkode.farmz.app.domain.AuthRepository
import com.kryptkode.farmz.app.utils.livedata.event.Event
import com.kryptkode.farmz.base.MainCoroutineRule
import com.kryptkode.farmz.utils.DataFactory.randomString
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Overrides Dispatchers.Main used in Coroutines
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private val authRepository: AuthRepository = mockk()

    private lateinit var viewModel: LoginViewModel

    private val testErrorMessage = randomString()

    @Before
    fun setUp() {
        viewModel = LoginViewModel(authRepository)
        stubRepositoryForSuccess()
    }

    @Test
    fun `login shows loading indicator`() {

        viewModel.login(randomString(), randomString())

        assertThat(viewModel.showLoading.value).isEqualTo(Event(Unit))
    }

    @Test
    fun `login on success hides loading indicator`() {

        viewModel.login(randomString(), randomString())

        assertThat(viewModel.hideLoading.value).isEqualTo(Event(Unit))
    }

    @Test
    fun `login on error hides loading indicator`() {
        stubRepositoryForError()

        viewModel.login(randomString(), randomString())

        assertThat(viewModel.hideLoading.value).isEqualTo(Event(Unit))
    }

    @Test
    fun `login on success calls next`() {

        viewModel.login(randomString(), randomString())

        assertThat(viewModel.goToNext.value).isEqualTo(Event(Unit))
    }

    @Test
    fun `login on error shows error message`() {
        stubRepositoryForError()

        viewModel.login(randomString(), randomString())

        assertThat(viewModel.showErrorMessage.value).isEqualTo(Event(testErrorMessage))
    }

    private fun stubRepositoryForSuccess(){
        coEvery {
            authRepository.login(any(), any())
        }returns DataState.Success(Unit)
    }

    private fun stubRepositoryForError(){
        coEvery {
            authRepository.login(any(), any())
        }returns DataState.Error(testErrorMessage)
    }
}