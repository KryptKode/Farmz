package com.kryptkode.farmz.app.di.controller

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.kryptkode.farmz.app.utils.PhoneNumberSanitizer
import com.kryptkode.farmz.app.utils.date.ServerDisplayedDateConverter
import com.kryptkode.farmz.navigation.NavControllerProvider
import com.kryptkode.farmz.screens.common.ViewFactory
import com.kryptkode.farmz.screens.common.imageloader.ImageLoader
import dagger.Module
import dagger.Provides

/**
 * Created by kryptkode on 5/21/2020.
 */
@Module
class ControllerModule(
    private val activity: AppCompatActivity,
    private val fragmentManager: FragmentManager
) {
    @Provides
    @ControllerScope
    fun context(): Context {
        return activity
    }

    @Provides
    @ControllerScope
    fun activity(): AppCompatActivity {
        return activity
    }

    @Provides
    @ControllerScope
    fun fragmentManager(): FragmentManager {
        return fragmentManager
    }

    @Provides
    @ControllerScope
    fun navControllerProvider(): NavControllerProvider {
        return activity as NavControllerProvider
    }

    @Provides
    @ControllerScope
    fun layoutInflater(): LayoutInflater {
        return activity.layoutInflater
    }

    @Provides
    @ControllerScope
    fun viewFactory(
        imageLoader: ImageLoader,
        phoneNumberSanitizer: PhoneNumberSanitizer,
        serverDisplayedDateConverter: ServerDisplayedDateConverter,
        layoutInflater: LayoutInflater
    ): ViewFactory {
        return ViewFactory(
            phoneNumberSanitizer,
            serverDisplayedDateConverter,
            imageLoader,
            layoutInflater
        )
    }
}