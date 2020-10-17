package com.kryptkode.farmz.screens.common.fragmentroot

import androidx.fragment.app.Fragment

interface FragmentRoot {
    val numberOfRootFragments: Int
    fun getFragmentRootResId():Int
    fun getRootFragment(index:Int):Fragment
}