package com.example.smart_home.presentation.cameras

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.smart_home.R
import com.example.smart_home.core.base.BaseFragment
import com.example.smart_home.databinding.FragmentCamerasBinding
import com.example.smart_home.presentation.utils.UiState
import dagger.hilt.android.AndroidEntryPoint
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CamerasFragment : BaseFragment<FragmentCamerasBinding>() {

    private val viewModel: CamerasViewModel by viewModels()

    private val adapter = CameraAdapter()
    override fun inflateViewBinding(): FragmentCamerasBinding =
        FragmentCamerasBinding.inflate(layoutInflater)

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

            @SuppressLint("NotifyDataSetChanged")
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.absoluteAdapterPosition
                if (direction == ItemTouchHelper.LEFT) {
                    adapter.deleteItem(position)
                } else if (direction == ItemTouchHelper.RIGHT) {
                    adapter.doFavoriteItem(position)
                }
                adapter.notifyDataSetChanged()
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
                            .addActionIcon(R.drawable.ic_delete)
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
        itemTouchHelper.attachToRecyclerView(binding.rvCameras)
    }

    override fun initRecyclerView() {
        super.initRecyclerView()
        binding.rvCameras.adapter = adapter
    }

    override fun initLiveData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.viewState.collect {
                when (it) {
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
        viewLifecycleOwner.lifecycleScope.launch { viewModel.getCameras() }
    }
}