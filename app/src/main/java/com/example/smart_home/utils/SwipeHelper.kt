package com.example.smart_home.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.RectF
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import java.util.Queue

abstract class SwipeHelper(dragDirs: Int, swipeDirs: Int) : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {
    var buttonWidth:Int? = null
    private lateinit var recyclerView:RecyclerView
    private lateinit var buttonList:List<MyButton>
    private val swipePosition = -1
    private val swipeThreshold = 0.5f
    private lateinit var buttonBuffer:Map<Int,List<MyButton>>
    private lateinit var removerQueue: Queue<Int>


    inner class MyButton(
        private val text:String,
        private val imageResId:Int,
        private val textSize:Int,
        private val color:Int,
        private val pos:Int,
        private val clickRegion:RectF,
        listener: MyButtonClickListener,
        private val context: Context,
        private val resources: Resources
    ) {


    }



}