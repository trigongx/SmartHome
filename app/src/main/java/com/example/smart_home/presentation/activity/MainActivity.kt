package com.example.smart_home.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.smart_home.R
import com.example.smart_home.databinding.ActivityMainBinding
import com.example.smart_home.presentation.cameras.CamerasFragment
import com.example.smart_home.presentation.doors.DoorsFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val fragmentsList = listOf(
        CamerasFragment.newInstance(),
        DoorsFragment.newInstance()
    )

    private val adapter = VPAdapter(this,fragmentsList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().add(R.id.nav_host_fragment, fragmentsList[0]).commit()

        binding.tabLayoutMain.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment,fragmentsList[tab?.position!!]).commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        /*val fragmentsTitleList = listOf(
            getString(R.string.cameras),getString(R.string.doors)
        )

        binding.viewPagerMain.adapter = adapter
        TabLayoutMediator(binding.tabLayoutMain,binding.viewPagerMain){tab,position ->
            tab.text = fragmentsTitleList[position]
        }.attach()*/

    }
}