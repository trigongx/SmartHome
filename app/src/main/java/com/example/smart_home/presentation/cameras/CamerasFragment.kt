package com.example.smart_home.presentation.cameras

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.smart_home.core.base.BaseFragment
import com.example.smart_home.core.network.RetrofitClient
import com.example.smart_home.data.repositories.RetrofitRepositoryImpl
import com.example.smart_home.data.storage.RetrofitStorageImpl
import com.example.smart_home.databinding.FragmentCamerasBinding
import com.example.smart_home.domain.usecases.GetAllCamerasUseCase

class CamerasFragment : BaseFragment<FragmentCamerasBinding, CamerasViewModel>() {

    private val retrofitRepository = RetrofitRepositoryImpl(RetrofitStorageImpl(RetrofitClient().createApiService()))

    private val getAllCamerasUseCase = GetAllCamerasUseCase(retrofitRepository)
    override fun inflateViewBinding(): FragmentCamerasBinding =
        FragmentCamerasBinding.inflate(layoutInflater)

    override fun setViewModel(): CamerasViewModel = CamerasViewModel(getAllCamerasUseCase)

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