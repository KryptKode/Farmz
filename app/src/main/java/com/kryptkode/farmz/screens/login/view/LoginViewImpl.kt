package com.kryptkode.farmz.screens.login.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.kryptkode.farmz.app.utils.extension.beGone
import com.kryptkode.farmz.app.utils.extension.beVisible
import com.kryptkode.farmz.app.utils.extension.hideKeyboard
import com.kryptkode.farmz.app.utils.extension.imeListener
import com.kryptkode.farmz.databinding.LayoutLoginBinding

class LoginViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : LoginView() {

    private val binding = LayoutLoginBinding.inflate(layoutInflater, parent, false)

    init {
        hideLoading()
        initListeners()
    }

    private fun initListeners() {
        binding.btnLogin.setOnClickListener {
            handleLogin()
        }

        binding.emailEditText.addTextChangedListener {
            clearEmailError()
        }


        binding.passwordEditText.addTextChangedListener {
            clearPasswordError()
        }

        binding.passwordEditText.imeListener {
            handleLogin()
        }
    }

    private fun handleLogin() {
        onEachListener {
            it.onLoginClick(
                binding.emailEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }
    }

    override fun showEmailError(message: String) {
        binding.emailIdInput.error = message
    }

    override fun showPasswordError(message: String) {
        binding.passwordInput.error = message
    }

    override fun hideLoading() {
        binding.progress.root.beGone()
    }

    override fun showLoading() {
        binding.progress.root.beVisible()
    }

    override fun hideKeyboard() {
        binding.root.hideKeyboard()
    }

    override fun clearErrors() {
        clearEmailError()
        clearPasswordError()
    }

    private fun clearPasswordError() {
        binding.passwordInput.error = null
    }

    private fun clearEmailError() {
        binding.emailIdInput.error = null
    }

    override val rootView: View
        get() = binding.root
}