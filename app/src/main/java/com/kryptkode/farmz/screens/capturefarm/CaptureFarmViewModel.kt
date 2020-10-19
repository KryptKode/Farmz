package com.kryptkode.farmz.screens.capturefarm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kryptkode.farmz.R
import com.kryptkode.farmz.app.data.state.DataState
import com.kryptkode.farmz.app.domain.farm.FarmRepository
import com.kryptkode.farmz.app.logger.Logger
import com.kryptkode.farmz.app.utils.StringResource
import com.kryptkode.farmz.app.utils.livedata.event.Event
import com.kryptkode.farmz.app.utils.livedata.extension.asLiveData
import com.kryptkode.farmz.screens.capturefarm.model.UiFarm
import com.kryptkode.farmz.screens.capturefarm.model.UiFarmLocation
import com.kryptkode.farmz.screens.capturefarm.model.UiFarmMapper
import kotlinx.coroutines.launch
import javax.inject.Inject

class CaptureFarmViewModel @Inject constructor(
    private val farmRepository: FarmRepository,
    private val uiFarmMapper: UiFarmMapper,
    private val stringResource: StringResource,
    private val logger: Logger
) : ViewModel() {

    private val mutableShowLoading = MutableLiveData<Event<Unit>>()
    val showLoading = mutableShowLoading.asLiveData()

    private val mutableHideLoading = MutableLiveData<Event<Unit>>()
    val hideLoading = mutableHideLoading.asLiveData()

    private val mutableShowErrorMessage = MutableLiveData<Event<String>>()
    val showErrorMessage = mutableShowErrorMessage.asLiveData()

    private val mutableGoToNext = MutableLiveData<Event<Unit>>()
    val goToNext = mutableGoToNext.asLiveData()

    private var farmLocation = mutableListOf<UiFarmLocation>()

    fun addFarm(uiFarm: UiFarm) {
        viewModelScope.launch {
            showLoading()
            try {
                farmRepository.addFarm(uiFarmMapper.mapViewToDomain(uiFarm))
                hideLoading()
                mutableGoToNext.postValue(Event(Unit))
            } catch (e: Exception) {
                hideLoading()
                logger.e("Error while adding farm", e)
                showErrorMessage(stringResource.getString(R.string.add_farm_error))
            }
        }
    }

    fun saveFarm(uiFarm: UiFarm) {
        viewModelScope.launch {
            showLoading()
            when (val result =
                farmRepository.updateFarmer(uiFarmMapper.mapViewToDomain(uiFarm))) {
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

    fun setLocation(coordinates: List<UiFarmLocation>) {
        farmLocation.clear()
        farmLocation.addAll(coordinates)
    }

    fun getLocation(): List<UiFarmLocation> {
        return farmLocation
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