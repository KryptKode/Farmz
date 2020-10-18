package com.kryptkode.farmz.screens.farmerdetails

import androidx.lifecycle.*
import com.kryptkode.farmz.app.domain.farmer.FarmerRepository
import com.kryptkode.farmz.screens.farmers.model.FarmerViewMapper
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FarmerDetailViewModel @Inject constructor(
    private val farmerRepository: FarmerRepository,
    private val farmerViewMapper: FarmerViewMapper
) : ViewModel() {

    private val farmerId = MutableLiveData<String>()

    val farmer = farmerId.switchMap { id ->
        farmerRepository.getFarmerById(id).map {
            farmerViewMapper.mapDomainToView(it)
        }.asLiveData(viewModelScope.coroutineContext)
    }

    fun getFarmer(id: String) {
        farmerId.postValue(id)
    }

}