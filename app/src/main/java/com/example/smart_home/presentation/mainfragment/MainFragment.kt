package com.example.smart_home.presentation.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.smart_home.R
import com.example.smart_home.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.viewPagerMain.adapter = VPAdapter(this)
        TabLayoutMediator(binding.tabLayoutMain,binding.viewPagerMain){tab,pos ->
            when(pos){
                0 -> tab.text = getString(R.string.cameras)
                1 -> tab.text = getString(R.string.doors)
            }
        }.attach()
    }

}