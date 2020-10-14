package com.kryptkode.flashalerts.app.di.service

import dagger.Subcomponent

/**
 * Created by kryptkode on 5/21/2020.
 */
@ServiceScope
@Subcomponent(
    modules = [
        ServiceModule::class
    ]
)
interface ServiceComponent {
}