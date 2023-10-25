package com.example.smart_home.presentation.mainfragment

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.smart_home.presentation.cameras.CamerasFragment
import com.example.smart_home.presentation.doors.DoorsFragment

class VPAdapter(fragment: Fragment):FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> CamerasFragment()
            else -> DoorsFragment()
        }
    }
}