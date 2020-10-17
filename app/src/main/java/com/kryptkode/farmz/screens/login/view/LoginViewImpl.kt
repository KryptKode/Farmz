package com.kryptkode.farmz.screens.login.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.kryptkode.farmz.databinding.LayoutLoginBinding

class LoginViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : LoginView() {

    private val binding = LayoutLoginBinding.inflate(layoutInflater, parent, false)

    init {
        initListeners()
    }

    private fun initListeners() {
        binding.btnLogin.setOnClickListener {
            onEachListener {
                it.onLoginClick(
                    binding.emailEditText.text.toString(),
                    binding.passwordEditText.text.toString()
                )
            }
        }

        binding.emailEditText.addTextChangedListener {
            clearEmailError()
        }


        binding.passwordEditText.addTextChangedListener {
            clearPasswordError()
        }
    }

    override fun showEmailError(message: String) {
        binding.emailIdInput.error = message
    }

    override fun showPasswordError(message: String) {
        binding.passwordInput.error = message
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