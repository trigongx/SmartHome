package com.example.smart_home.presentation.doors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.smart_home.data.model.DoorModel
import com.example.smart_home.databinding.ItemRvDoorsBinding

class DoorAdapter(private val itemList: ArrayList<DoorModel>) :
    RecyclerView.Adapter<DoorAdapter.DoorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoorViewHolder {
        return DoorViewHolder(
            ItemRvDoorsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: DoorViewHolder, position: Int) {
        holder.toBind(itemList[position])
    }

    fun doFavoriteItem(adapterPosition: Int) {
        val item = itemList.removeAt(adapterPosition)
        itemList.add(0, item)
        notifyItemMoved(adapterPosition, 0)
    }

    fun editItem(adapterPosition: Int) {
        itemList.removeAt(adapterPosition)
        notifyItemRemoved(adapterPosition)
    }

    inner class DoorViewHolder(private val binding: ItemRvDoorsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun toBind(doorModel: DoorModel) {
            binding.tvNameDoor.text = doorModel.nameDoor
            binding.imgDoor.setImageResource(doorModel.image)
            binding.tvIsOnline.text = doorModel.isOnline
            itemView.setOnClickListener {
                if(binding.imgDoor.visibility == View.GONE && binding.btnPlay.visibility == View.GONE){
                    showDetails()
                } else {
                    hideDetails()
                }

            }
        }

        private fun hideDetails() {
            binding.imgDoor.visibility = View.GONE
            binding.btnPlay.visibility = View.GONE
        }

        private fun showDetails() {
            binding.imgDoor.visibility = View.VISIBLE
            binding.btnPlay.visibility = View.VISIBLE
        }
    }
}