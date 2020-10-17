package com.kryptkode.farmz.screens.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.MutableLiveData
import com.kryptkode.farmz.app.utils.ToastHelper
import com.kryptkode.farmz.app.utils.Validator
import com.kryptkode.farmz.app.utils.livedata.event.Event
import com.kryptkode.farmz.navigation.login.LoginNavigator
import com.kryptkode.farmz.screens.login.view.LoginView
import com.kryptkode.farmz.utils.DataFactory.randomString
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class LoginControllerTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var controller: LoginController

    private val loginNavigator: LoginNavigator = mockk()
    private val toastHelper: ToastHelper = mockk()
    private val validator: Validator = mockk()
    private val loginViewModel: LoginViewModel = mockk()
    private val lifecycleOwner: LifecycleOwner = mockk()
    private val lifecycle = LifecycleRegistry(lifecycleOwner)
    private val loginView: LoginView = mockk()

    @Before
    fun setUp() {
        controller = LoginController(loginNavigator, toastHelper, validator)
        stubLoginRegisterListener()
        stubLoginUnregisterListener()
        setupController()
        stubViewModelLiveData()
        stubValidatorLiveData()
        stubLifeCycle()
        stubValidatorSuccess()
        stubLoginViewClearErrors()
        stubViewModelLogin()
    }

    @Test
    fun `onStart registers the view listener`() {

        controller.onStart()

        verify {
            loginView.registerListener(controller)
        }
    }

    @Test
    fun `onStart registers the viewModel observers`() {
        controller.onStart()

        verify {
            loginViewModel.showLoading
            loginViewModel.hideLoading
            loginViewModel.goToNext
            loginViewModel.showErrorMessage
        }
    }

    @Test
    fun `onStart registers the validator observers`() {
        controller.onStart()

        verify {
            validator.emailError
            validator.passwordError
        }
    }

    @Test
    fun `onStop unregisters the view listener`() {

        controller.onStop()

        verify {
            loginView.unregisterListener(controller)
        }
    }

    @Test
    fun `onLoginClick calls validator`() {

        val email = randomString()
        val password = randomString()

        controller.onLoginClick(email, password)

        verify {
            validator.validateLogin(email, password)
        }
    }

    @Test
    fun `onLoginClick calls viewModel when validation succeeds`() {
        val email = randomString()
        val password = randomString()

        controller.onLoginClick(email, password)

        verify {
            loginViewModel.login(email, password)
        }
    }


    @Test
    fun `onLoginClick does not call viewModel when validation fails`() {
        stubValidatorFailure()
        val email = randomString()
        val password = randomString()

        controller.onLoginClick(email, password)

        verify(exactly = 0) {
            loginViewModel.login(email, password)
        }
    }

    private fun setupController() {
        controller.bindView(loginView)
        controller.bindViewModel(loginViewModel)
        controller.bindLifeCycleOwner(lifecycleOwner)
    }

    private fun stubLoginRegisterListener() {
        every {
            loginView.registerListener(any())
        } returns Unit
    }

    private fun stubLoginUnregisterListener() {
        every {
            loginView.unregisterListener(any())
        } returns Unit
    }

    private fun stubViewModelLiveData() {
        every {
            loginViewModel.showLoading
        } returns MutableLiveData(Event(Unit))

        every {
            loginViewModel.hideLoading
        } returns MutableLiveData(Event(Unit))

        every {
            loginViewModel.goToNext
        } returns MutableLiveData(Event(Unit))

        every {
            loginViewModel.showErrorMessage
        } returns MutableLiveData(Event(randomString()))
    }

    private fun stubValidatorLiveData() {
        every {
            validator.emailError
        } returns MutableLiveData(Event(randomString()))

        every {
            validator.passwordError
        } returns MutableLiveData(Event(randomString()))
    }

    private fun stubValidatorSuccess() {
        every {
            validator.validateLogin(any(), any())
        } returns true
    }

    private fun stubValidatorFailure() {
        every {
            validator.validateLogin(any(), any())
        } returns false
    }

    private fun stubLoginViewClearErrors() {
        every {
            loginView.clearErrors()
        } returns Unit
    }

    private fun stubViewModelLogin() {
        every {
            loginViewModel.login(any(), any())
        } returns Unit
    }

    private fun stubLifeCycle() {
        every {
            lifecycleOwner.lifecycle
        } returns lifecycle
    }

}