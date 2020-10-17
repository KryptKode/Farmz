package com.kryptkode.farmz.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kryptkode.farmz.app.domain.AuthRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    fun login(email:String, password:String){
        viewModelScope.launch {

        }
    }

}