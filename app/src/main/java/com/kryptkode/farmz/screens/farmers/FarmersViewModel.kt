package com.kryptkode.farmz.screens.farmers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.kryptkode.farmz.app.domain.farmer.FarmerRepository
import com.kryptkode.farmz.screens.farmers.model.FarmerView
import com.kryptkode.farmz.screens.farmers.model.FarmerViewMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FarmersViewModel @Inject constructor(
    private val farmerRepository: FarmerRepository,
    private val farmerViewMapper: FarmerViewMapper
) : ViewModel() {

    fun loadFarmers(): Flow<PagingData<FarmerView>> {
        return farmerRepository.getFarmers().map { pagingData ->
            pagingData.map { farmerViewMapper.mapDomainToView(it) }
        }.cachedIn(viewModelScope)
    }

}