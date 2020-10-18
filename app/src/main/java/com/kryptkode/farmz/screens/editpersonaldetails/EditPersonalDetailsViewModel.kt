package com.kryptkode.farmz.screens.editpersonaldetails

import androidx.lifecycle.*
import com.kryptkode.farmz.app.data.state.DataState
import com.kryptkode.farmz.app.domain.farmer.FarmerRepository
import com.kryptkode.farmz.app.utils.livedata.event.Event
import com.kryptkode.farmz.app.utils.livedata.extension.asLiveData
import com.kryptkode.farmz.screens.farmers.model.FarmerView
import com.kryptkode.farmz.screens.farmers.model.FarmerViewMapper
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditPersonalDetailsViewModel @Inject constructor(
    private val farmerRepository: FarmerRepository,
    private val farmerViewMapper: FarmerViewMapper
) : ViewModel() {

    private val mutableShowLoading = MutableLiveData<Event<Unit>>()
    val showLoading = mutableShowLoading.asLiveData()

    private val mutableHideLoading = MutableLiveData<Event<Unit>>()
    val hideLoading = mutableHideLoading.asLiveData()

    private val mutableShowErrorMessage = MutableLiveData<Event<String>>()
    val showErrorMessage = mutableShowErrorMessage.asLiveData()

    private val mutableGoToNext = MutableLiveData<Event<Unit>>()
    val goToNext = mutableGoToNext.asLiveData()


    private val farmerId = MutableLiveData<String>()

    val farmer = farmerId.switchMap { id ->
        farmerRepository.getFarmerById(id).map {
            farmerViewMapper.mapDomainToView(it)
        }.asLiveData(viewModelScope.coroutineContext)
    }

    fun getFarmer(id: String) {
        farmerId.postValue(id)
    }

    fun save(farmerView: FarmerView) {
        viewModelScope.launch {
            showLoading()
            when (val result =
                farmerRepository.updateFarmer(farmerViewMapper.mapViewToDomain(farmerView))) {
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