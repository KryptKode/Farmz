package com.kryptkode.farmz.app.di.service

import javax.inject.Scope

/**
 * Custom scope for global application singletons
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ServiceScope
