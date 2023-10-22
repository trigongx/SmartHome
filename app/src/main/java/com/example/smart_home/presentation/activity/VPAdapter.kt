package com.example.smart_home.presentation.activity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class VPAdapter(
    fa:FragmentActivity,
    private val list:List<Fragment>
):FragmentStateAdapter(fa) {
    override fun getItemCount() = list.size

    override fun createFragment(position: Int): Fragment {
        return list[position]
    }
}