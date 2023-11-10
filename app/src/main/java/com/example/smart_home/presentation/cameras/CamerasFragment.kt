package com.example.smart_home.presentation.cameras

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.smart_home.core.base.BaseFragment
import com.example.smart_home.databinding.FragmentCamerasBinding
import com.example.smart_home.presentation.utils.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CamerasFragment : BaseFragment<FragmentCamerasBinding>() {

    private val viewModel:CamerasViewModel by viewModels()

    private val adapter = CameraAdapter()
    override fun inflateViewBinding(): FragmentCamerasBinding =
        FragmentCamerasBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRequest()
    }

    override fun initRecyclerView() {
        super.initRecyclerView()
        binding.rvCameras.adapter = adapter
    }

    override fun initLiveData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.viewState.collect{
                when (it){
                    is UiState.Loading -> {
                        binding.shimmer.startShimmer()
                        binding.shimmer.visibility = View.VISIBLE
                    }
                    is UiState.Success -> {
                        binding.shimmer.stopShimmer()
                        binding.shimmer.visibility = View.GONE
                        adapter.addData(it.data?.data?.cameras!!)
                    }
                    is UiState.Empty -> {
                        Toast.makeText(requireContext(), "Empty", Toast.LENGTH_SHORT).show()
                    }
                    is UiState.Error -> {
                        Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }
    }


    private fun initRequest() {
        viewLifecycleOwner.lifecycleScope.launch{viewModel.getCameras()}
    }
}