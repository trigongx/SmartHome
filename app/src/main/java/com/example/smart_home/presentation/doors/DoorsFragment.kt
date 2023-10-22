package com.example.smart_home.presentation.doors

import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.smart_home.R
import com.example.smart_home.data.model.DoorModel
import com.example.smart_home.databinding.FragmentDoorsBinding
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator


class DoorsFragment : Fragment() {

    private lateinit var binding: FragmentDoorsBinding
    private val doorsList = arrayListOf(
        DoorModel(R.color.black, "Подъезд 1", "В сети"),
        DoorModel(R.color.black, "Выход на пожарную лестницу", "В сети"),
        DoorModel(R.color.black, "Подъезд 2", "Не в сети"),
        DoorModel(R.color.black, "Домофон", "В сети")
    )

    private val adapter = DoorAdapter(doorsList)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDoorsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvDoors.adapter = adapter
        val itemTouchHelper = ItemTouchHelper(object :ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == ItemTouchHelper.LEFT) adapter.editItem(viewHolder.adapterPosition)
                else adapter.doFavoriteItem(viewHolder.adapterPosition)
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
                    if (dX > 0){
                RecyclerViewSwipeDecorator.Builder(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
                    .addBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.red
                        )
                    )
                    .addActionIcon(R.drawable.ic_favorite)
                    .create()
                    .decorate()
                }
                else{
                    RecyclerViewSwipeDecorator.Builder(
                        c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )
                        .addBackgroundColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.grey
                            )
                        )
                        .addActionIcon(R.drawable.ic_edit)
                        .create()
                        .decorate()
                    }
                }
                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        })
        itemTouchHelper.attachToRecyclerView(binding.rvDoors)
    }

    companion object {
        @JvmStatic
        fun newInstance() = DoorsFragment()
    }
}