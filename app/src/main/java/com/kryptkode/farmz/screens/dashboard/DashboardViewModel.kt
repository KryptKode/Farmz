package com.kryptkode.farmz.screens.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.kryptkode.farmz.app.domain.farm.FarmRepository
import com.kryptkode.farmz.app.utils.date.DisplayedDateTimeFormatter
import com.kryptkode.farmz.app.utils.livedata.event.Event
import com.kryptkode.farmz.app.utils.livedata.extension.asLiveData
import com.kryptkode.farmz.screens.farm.model.UiFarmMapper
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    farmRepository: FarmRepository,
    private val uiFarmMapper: UiFarmMapper,
    private val displayedDateFormatter: DisplayedDateTimeFormatter,
) : ViewModel() {

    val farms = farmRepository.getLastUpdatedFarms(FARMS_LIMIT).map { data ->
        data.map { uiFarmMapper.mapDomainToView(it) }
    }.cachedIn(viewModelScope).catch {

    }.asLiveData()

    val lastUpdatedDate = farmRepository.getLastUpdatedDate().map {
        displayedDateFormatter.formatToDisplayedDate(it)
    }.catch {

    }.asLiveData()

    val farmsCount = farmRepository.getFarmsCount().catch {

    }.asLiveData()

    val capturedFarmersCount = farmRepository.getCapturedFarmerCount().catch {

    }.asLiveData()

    private val mutableShowErrorMessage = MutableLiveData<Event<String>>()
    val showErrorMessage = mutableShowErrorMessage.asLiveData()

    private fun showErrorMessage(@Suppress("SameParameterValue") message: String) {
        mutableShowErrorMessage.postValue(Event(message))
    }

    companion object {
        private const val FARMS_LIMIT = 7
    }

}