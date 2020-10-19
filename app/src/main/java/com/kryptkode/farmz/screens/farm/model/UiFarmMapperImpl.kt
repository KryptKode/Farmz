package com.kryptkode.farmz.screens.farm.model

import com.kryptkode.farmz.app.domain.farm.Farm
import com.kryptkode.farmz.app.domain.farm.FarmLocation
import com.kryptkode.farmz.app.utils.date.DisplayedDateFormatter
import javax.inject.Inject

class UiFarmMapperImpl @Inject constructor(
    private val displayedDateFormatter: DisplayedDateFormatter
) : UiFarmMapper {

    override fun mapViewToDomain(farm: UiFarm): Farm {
        return Farm(
            farm.id,
            farm.farmerId,
            farm.name,
            farm.location,
            farm.farmCoordinates.map {
                FarmLocation(
                    it.latitude,
                    it.longitude
                )
            },
            displayedDateFormatter.parseDisplayedDate(farm.dateCreated)!!,
            displayedDateFormatter.parseDisplayedDate(farm.dateLastUpdated)!!,
        )
    }

    override fun mapDomainToView(farm: Farm): UiFarm {
        return UiFarm(
            farm.id,
            farm.farmerId,
            farm.name,
            farm.location,
            farm.farmCoordinates.map {
                UiFarmLocation(it.latitude, it.longitude)
            },
            displayedDateFormatter.formatToDisplayedDate(farm.dateCreated),
            displayedDateFormatter.formatToDisplayedDate(farm.dateLastUpdated),
        )
    }
}