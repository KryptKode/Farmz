package com.kryptkode.farmz.base


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

abstract class BaseTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

}