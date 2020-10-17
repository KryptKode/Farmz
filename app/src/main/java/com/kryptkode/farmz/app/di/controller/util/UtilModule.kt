package com.kryptkode.farmz.app.di.controller.util

import android.content.Context
import com.kryptkode.farmz.app.di.controller.ControllerScope
import com.kryptkode.farmz.app.utils.NetworkListener
import com.kryptkode.farmz.app.utils.ToastHelper
import com.kryptkode.farmz.app.utils.Validator
import com.kryptkode.farmz.app.utils.VersionUtil
import com.kryptkode.farmz.screens.common.imageloader.ImageLoader
import com.kryptkode.farmz.screens.common.imageloader.ImageLoaderImpl
import dagger.Module
import dagger.Provides

@Module
class UtilModule {

    @Provides
    @ControllerScope
    fun provideToastHelper(context: Context): ToastHelper {
        return ToastHelper(context)
    }

    @Provides
    @ControllerScope
    fun provideNetworkListener(context: Context): NetworkListener {
        return NetworkListener(context)
    }

    @Provides
    @ControllerScope
    fun provideValidator(context: Context): Validator {
        return Validator(context)
    }

    @Provides
    @ControllerScope
    fun provideVersionUtil(context: Context): VersionUtil {
        return VersionUtil(context)
    }

    @Provides
    @ControllerScope
    fun provideImageLoader(): ImageLoader {
        return ImageLoaderImpl()
    }
}