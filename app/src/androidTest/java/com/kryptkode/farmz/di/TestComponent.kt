package com.kryptkode.farmz.di

import com.kryptkode.farmz.app.TestApp
import com.kryptkode.farmz.app.di.app.ApplicationComponent
import com.kryptkode.farmz.app.di.app.ApplicationScope
import com.kryptkode.farmz.di.app.TestAppModule
import com.kryptkode.farmz.di.repo.TestRepoModule
import dagger.Component

@ApplicationScope
@Component(modules = [TestAppModule::class, TestRepoModule::class])
interface TestComponent : ApplicationComponent {
    fun inject(testApp: TestApp)
}