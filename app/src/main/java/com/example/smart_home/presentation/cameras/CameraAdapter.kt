package com.example.smart_home.presentation.cameras

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.smart_home.data.model.CameraModel
import com.example.smart_home.databinding.ItemRvCamerasBinding

class CameraAdapter :
    RecyclerView.Adapter<CameraAdapter.CameraViewHolder>() {

    private var list = mutableListOf<CameraModel.Data.Camera>()

    @SuppressLint("NotifyDataSetChanged")
    fun addData(cameras: List<CameraModel.Data.Camera>){
        list.clear()
        list.addAll(cameras)
        notifyDataSetChanged()
    }

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
        fun toBind(camera: CameraModel.Data.Camera) {
            binding.tvNameCamera.text = camera.name
            binding.imgCamera.load(camera.snapshot)
            if (camera.favorites) binding.ivFavorites.visibility = View.VISIBLE
            else binding.ivFavorites.visibility = View.GONE
        }
    }
}