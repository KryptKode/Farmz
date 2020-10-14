package com.kryptkode.flashalerts.app.di.controller

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
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
}