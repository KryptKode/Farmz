package com.kryptkode.farmz.app.di.app

import com.kryptkode.farmz.app.di.app.repo.RepositoryModule
import com.kryptkode.farmz.app.di.controller.ControllerComponent
import com.kryptkode.farmz.app.di.controller.ControllerModule
import com.kryptkode.farmz.app.di.service.ServiceComponent
import com.kryptkode.farmz.app.di.service.ServiceModule
import dagger.Component

/**
 * Created by kryptkode on 5/21/2020.
 */
@ApplicationScope
@Component(
    modules = [
        ApplicationModule::class,
        RepositoryModule::class
    ]
)
interface ApplicationComponent {
    fun controllerComponent(controllerModule: ControllerModule): ControllerComponent
    fun serviceComponent(serviceModule: ServiceModule): ServiceComponent
}