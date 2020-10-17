package com.kryptkode.farmz.screens.common.view

import java.util.*

abstract class BaseObservableViewMvc<ListenerType> : BaseViewMvc(),
    ObservableViewMvc<ListenerType> {
    private val listenersSet: MutableSet<ListenerType> =
        HashSet()

    override fun registerListener(listener: ListenerType) {
        this.listenersSet.add(listener)
    }

    override fun unregisterListener(listener: ListenerType) {
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