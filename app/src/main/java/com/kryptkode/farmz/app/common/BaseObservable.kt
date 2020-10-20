package com.kryptkode.farmz.app.common

import java.util.*

abstract class BaseObservable<ListenerType> {

    private val listenersSet: MutableSet<ListenerType> =
        HashSet()

    fun registerListener(listener: ListenerType) {
        this.listenersSet.add(listener)
    }

    fun unregisterListener(listener: ListenerType) {
        this.listenersSet.remove(listener)
    }

    protected fun onEachListener(func: (ListenerType) -> Unit) {
        listeners.forEach {
            func(it)
        }
    }

    protected val listeners: Set<ListenerType>
        get() = Collections.unmodifiableSet(this.listenersSet)

}