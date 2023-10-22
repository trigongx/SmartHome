package com.example.smart_home.presentation.cameras

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smart_home.R
import com.example.smart_home.data.model.CameraModel
import com.example.smart_home.databinding.FragmentCamerasBinding

class CamerasFragment : Fragment() {

    private lateinit var binding: FragmentCamerasBinding

    private val cameraList = listOf(
        CameraModel(R.color.black,"Камера 1"),
        CameraModel(R.color.black,"Камера 2"),
        CameraModel(R.color.black,"Камера 3"),
        CameraModel(R.color.black,"Камера 4"),
        CameraModel(R.color.black,"Камера 5"),
    )

    private val adapter = CameraAdapter(cameraList)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCamerasBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCameras.adapter = adapter
    }

    companion object{
        @JvmStatic
        fun newInstance() = CamerasFragment()
    }
}