package com.kryptkode.farmz.screens.farmers.list

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kryptkode.farmz.screens.common.ViewFactory
import com.kryptkode.farmz.screens.farmers.model.FarmerView
import com.kryptkode.farmz.screens.farmers.view.FarmerListItemView

class FarmersListAdapter (
    private val viewFactory: ViewFactory,
    private val onClickItem: (FarmerView) -> Unit
) : PagingDataAdapter<FarmerView, FarmersListAdapter.FarmerListViewHolder>(UIMODEL_COMPARATOR),
    FarmerListItemView.Listener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarmerListViewHolder {
        val viewHolder = FarmerListViewHolder(viewFactory.getFarmerItemView(parent))
        viewHolder.farmerListView.registerListener(this)
        return viewHolder
    }

    override fun onBindViewHolder(holder: FarmerListViewHolder, position: Int) {
        holder.farmerListView.bindFarmer(getItem(position))
    }

    override fun onItemClick(item: FarmerView) {
        onClickItem(item)
    }

    class FarmerListViewHolder (
        val farmerListView: FarmerListItemView
    ) : RecyclerView.ViewHolder(farmerListView.rootView)

    companion object {
        private val UIMODEL_COMPARATOR = object : DiffUtil.ItemCallback<FarmerView>() {
            override fun areItemsTheSame(oldItem: FarmerView, newItem: FarmerView): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: FarmerView, newItem: FarmerView): Boolean{
                return  oldItem == newItem
            }

        }
    }

}