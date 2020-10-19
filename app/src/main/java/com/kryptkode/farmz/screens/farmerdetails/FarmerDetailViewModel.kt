package com.kryptkode.farmz.screens.farmerdetails

import androidx.lifecycle.*
import androidx.paging.cachedIn
import androidx.paging.map
import com.kryptkode.farmz.R
import com.kryptkode.farmz.app.data.state.DataState
import com.kryptkode.farmz.app.domain.farm.FarmRepository
import com.kryptkode.farmz.app.domain.farmer.FarmerRepository
import com.kryptkode.farmz.app.logger.Logger
import com.kryptkode.farmz.app.utils.StringResource
import com.kryptkode.farmz.app.utils.livedata.event.Event
import com.kryptkode.farmz.app.utils.livedata.extension.asLiveData
import com.kryptkode.farmz.screens.capturefarm.model.UiFarmMapper
import com.kryptkode.farmz.screens.farmers.model.FarmerView
import com.kryptkode.farmz.screens.farmers.model.FarmerViewMapper
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class FarmerDetailViewModel @Inject constructor(
    private val farmerRepository: FarmerRepository,
    private val farmerViewMapper: FarmerViewMapper,
    private val farmRepository: FarmRepository,
    private val uiFarmMapper: UiFarmMapper,
    private val stringResource: StringResource,
    private val logger: Logger
) : ViewModel() {

    private val farmerId = MutableLiveData<String>()

    private val mutableShowErrorMessage = MutableLiveData<Event<String>>()
    val showErrorMessage = mutableShowErrorMessage.asLiveData()


    val farmer = farmerId.switchMap { id ->
        farmerRepository.getFarmerById(id).map {
            farmerViewMapper.mapDomainToView(it)
        }.asLiveData(viewModelScope.coroutineContext)
    }

    val farms = farmerId.switchMap { id ->
        farmRepository.getFarmsByFarmer(id).map { data ->
            data.map { uiFarmMapper.mapDomainToView(it) }
        }.cachedIn(viewModelScope).asLiveData()

    }

    fun getFarmer(id: String) {
        farmerId.postValue(id)
    }

    fun updatePassport(farmerView: FarmerView) {
        viewModelScope.launch {
            when (farmerRepository.updateFarmer(farmerViewMapper.mapViewToDomain(farmerView))) {
                is DataState.Success -> {
                    logger.d("Updated passport successfully")
                }

                is DataState.Error -> {
                    showErrorMessage(stringResource.getString(R.string.photo_update_failed))
                }

                DataState.Loading -> {

                }
            }
        }
    }

    private fun showErrorMessage(@Suppress("SameParameterValue") message: String) {
        mutableShowErrorMessage.postValue(Event(message))
    }


}