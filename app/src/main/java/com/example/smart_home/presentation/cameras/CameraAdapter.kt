package com.example.smart_home.presentation.cameras

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smart_home.data.model.CameraModel
import com.example.smart_home.databinding.ItemRvCamerasBinding

class CameraAdapter(private val list: List<CameraModel>) :
    RecyclerView.Adapter<CameraAdapter.CameraViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CameraViewHolder {
        return CameraViewHolder(
            ItemRvCamerasBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: CameraViewHolder, position: Int) {
        holder.toBind(list[position])
    }

    inner class CameraViewHolder(private val binding: ItemRvCamerasBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun toBind(cameraModel: CameraModel) {
            binding.tvNameCamera.text = cameraModel.cameraName
        }
    }
}