package com.kryptkode.farmz.di

import com.kryptkode.farmz.app.TestApp
import com.kryptkode.farmz.app.di.app.ApplicationComponent
import com.kryptkode.farmz.app.di.app.ApplicationModule
import com.kryptkode.farmz.app.di.app.ApplicationScope
import com.kryptkode.farmz.app.di.app.mapper.MapperModule
import com.kryptkode.farmz.app.di.app.network.NetworkModule
import com.kryptkode.farmz.app.di.app.persistence.PersistenceModule
import com.kryptkode.farmz.app.di.app.repo.RepositoryModule
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        ApplicationModule::class,
        RepositoryModule::class,
        NetworkModule::class,
        PersistenceModule::class,
        MapperModule::class,
    ]
)
interface TestComponent : ApplicationComponent {
    fun inject(testApp: TestApp)
}