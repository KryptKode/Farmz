package com.kryptkode.farmz.screens.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kryptkode.farmz.app.data.state.DataState
import com.kryptkode.farmz.app.domain.AuthRepository
import com.kryptkode.farmz.app.utils.livedata.event.Event
import com.kryptkode.farmz.app.utils.livedata.extension.asLiveData
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val mutableShowLoading = MutableLiveData<Event<Unit>>()
    val showLoading = mutableShowLoading.asLiveData()

    private val mutableHideLoading = MutableLiveData<Event<Unit>>()
    val hideLoading = mutableHideLoading.asLiveData()

    private val mutableShowErrorMessage = MutableLiveData<Event<String>>()
    val showErrorMessage = mutableShowErrorMessage.asLiveData()

    private val mutableGoToNext = MutableLiveData<Event<Unit>>()
    val goToNext = mutableGoToNext.asLiveData()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            showLoading()
            when (val result = authRepository.login(email, password)) {
                is DataState.Success -> {
                    hideLoading()
                    mutableGoToNext.postValue(Event(Unit))
                }

                is DataState.Error -> {
                    hideLoading()
                    showErrorMessage(result.message)
                }
            }
        }
    }

    private fun showLoading() {
        mutableShowLoading.postValue(Event(Unit))
    }

    private fun hideLoading() {
        mutableHideLoading.postValue(Event(Unit))
    }

    private fun showErrorMessage(message: String) {
        mutableShowErrorMessage.postValue(Event(message))
    }

}