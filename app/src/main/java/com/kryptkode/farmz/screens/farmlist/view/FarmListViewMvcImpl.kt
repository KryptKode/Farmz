package com.kryptkode.farmz.screens.farmlist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kryptkode.farmz.databinding.LayoutFarmsBinding
import com.kryptkode.farmz.screens.capturefarm.model.UiFarm
import com.kryptkode.farmz.screens.common.ViewFactory

class FarmListViewMvcImpl(
    viewFactory: ViewFactory,
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : FarmListViewMvc() {
    private val binding = LayoutFarmsBinding.inflate(layoutInflater, parent, false)

    private val adapter = FarmListAdapter(viewFactory) { item ->
        onEachListener {
            it.onItemClick(item)
        }
    }

    init {
        binding.recyclerView.adapter = adapter
        binding.swipeRefresh.setOnRefreshListener {
            onEachListener {
                it.onRefresh()
            }
        }
    }

    override fun bindFarms(farms: List<UiFarm>) {
        adapter.submitList(farms)
    }

    override val rootView: View
        get() = binding.recyclerView
}