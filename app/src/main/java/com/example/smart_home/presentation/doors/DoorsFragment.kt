package com.example.smart_home.presentation.doors

import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.smart_home.R
import com.example.smart_home.core.base.BaseFragment
import com.example.smart_home.databinding.FragmentDoorsBinding
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator


class DoorsFragment : BaseFragment<FragmentDoorsBinding, DoorsViewModel>() {
    override fun inflateViewBinding(): FragmentDoorsBinding =
        FragmentDoorsBinding.inflate(layoutInflater)

    override fun setViewModel(): DoorsViewModel = DoorsViewModel()
    private val adapter = DoorAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRequest()
        val itemTouchHelper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == ItemTouchHelper.LEFT) adapter.editItem(viewHolder.adapterPosition)
                if (direction == ItemTouchHelper.RIGHT) adapter.doFavoriteItem(viewHolder.adapterPosition)
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
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    if (dX > 0) {
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
                    } else {
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

    override fun initRecyclerView() {
        super.initRecyclerView()
        binding.rvDoors.adapter = adapter
    }

    private fun initRequest() {
        viewModel.getDoors()
    }

    override fun initLiveData() {
        viewModel.doors.observe(viewLifecycleOwner) { doors ->
            adapter.addData(doors.data)
        }
        viewModel.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }
        viewModel.loading.observe(viewLifecycleOwner){loading ->
            if (loading){
                binding.shimmer.startShimmer()
                binding.shimmer.visibility = View.VISIBLE
            } else{
                binding.shimmer.stopShimmer()
                binding.shimmer.visibility = View.GONE
            }
        }
    }

}