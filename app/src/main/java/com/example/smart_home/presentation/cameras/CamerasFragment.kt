package com.example.smart_home.presentation.cameras

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.smart_home.core.base.BaseFragment
import com.example.smart_home.databinding.FragmentCamerasBinding
import com.example.smart_home.presentation.activity.MainActivity

class CamerasFragment : BaseFragment<FragmentCamerasBinding, CamerasViewModel>() {
    override fun inflateViewBinding(): FragmentCamerasBinding =
        FragmentCamerasBinding.inflate(layoutInflater)

    override fun setViewModel(): CamerasViewModel = CamerasViewModel(MainActivity.repository)

    private val adapter = CameraAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRequest()
    }

    override fun initRecyclerView() {
        super.initRecyclerView()
        binding.rvCameras.adapter = adapter
    }

    override fun initLiveData() {
        viewModel.cameras.observe(viewLifecycleOwner) { cameras ->
            Log.e("denn", "initLiveData: ${cameras.data.cameras}")
            adapter.addData(cameras.data.cameras)
        }
        viewModel.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }
        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading){
                binding.shimmer.startShimmer()
                binding.shimmer.visibility = View.VISIBLE
            } else{
                binding.shimmer.stopShimmer()
                binding.shimmer.visibility = View.GONE
            }
        }
    }

    private fun initRequest() {
        viewModel.getCameras()
    }


}