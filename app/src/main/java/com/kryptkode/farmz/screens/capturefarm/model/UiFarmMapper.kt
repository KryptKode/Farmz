package com.kryptkode.farmz.screens.capturefarm.model

import com.kryptkode.farmz.app.domain.farm.Farm

interface UiFarmMapper {
    fun mapViewToDomain(farm: UiFarm): Farm
    fun mapDomainToView(farm: Farm): UiFarm
}