package com.kryptkode.farmz.screens.dashboard

import androidx.lifecycle.ViewModel
import com.kryptkode.farmz.app.domain.farm.FarmRepository
import com.kryptkode.farmz.app.domain.farmer.FarmerRepository
import com.kryptkode.farmz.app.logger.Logger
import com.kryptkode.farmz.app.utils.StringResource
import com.kryptkode.farmz.screens.farm.model.UiFarmMapper
import com.kryptkode.farmz.screens.farmers.model.FarmerViewMapper
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val farmerRepository: FarmerRepository,
    private val farmerViewMapper: FarmerViewMapper,
    private val farmRepository: FarmRepository,
    private val uiFarmMapper: UiFarmMapper,
    private val stringResource: StringResource,
    private val logger: Logger
) : ViewModel(){



}