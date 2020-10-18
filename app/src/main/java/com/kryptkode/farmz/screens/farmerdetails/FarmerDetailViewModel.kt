package com.kryptkode.farmz.screens.farmerdetails

import androidx.lifecycle.ViewModel
import com.kryptkode.farmz.app.domain.farmer.FarmerRepository
import com.kryptkode.farmz.screens.farmers.model.FarmerView
import com.kryptkode.farmz.screens.farmers.model.FarmerViewMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FarmerDetailViewModel @Inject constructor(
    private val farmerRepository: FarmerRepository,
    private val farmerViewMapper: FarmerViewMapper
) : ViewModel() {
    fun getFarmer(id: String): Flow<FarmerView> {
        return farmerRepository.getFarmerById(id).map {
            farmerViewMapper.mapDomainToView(it)
        }
    }

}