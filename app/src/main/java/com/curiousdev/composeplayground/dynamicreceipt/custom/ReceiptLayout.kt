package com.curiousdev.composeplayground.dynamicreceipt.custom

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.view.ViewTreeObserver
import android.view.ViewTreeObserver.*
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.platform.ComposeView
import com.curiousdev.composeplayground.R
import com.curiousdev.composeplayground.dynamicreceipt.model.MyPair
import com.curiousdev.composeplayground.dynamicreceipt.model.MyTransaction
import com.curiousdev.composeplayground.dynamicreceipt.screen.ReceiptPage

@SuppressLint("ViewConstructor")
@ExperimentalMaterialApi
class ReceiptLayout(ctx: Context,private val response: MutableList<MyPair>, onBitmapCreated: (bitmap: Bitmap) -> Unit) : LinearLayoutCompat(ctx) {

    constructor(ctx: Context) : this(ctx, mutableListOf(),onBitmapCreated = {})
    init {
        val height = 1000
        val width = 600
        val view = ComposeView(ctx)
        view.visibility = View.GONE
        view.layoutParams = LayoutParams(width, height)
        this.addView(view)

        view.setContent {
            ReceiptPage(response)
        }

        viewTreeObserver.addOnGlobalLayoutListener(object :
            OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val graphicUtils = GraphicUtils()
                val bitmap = graphicUtils.createBitmapFromView(view = view, width = width, height = height)
                onBitmapCreated(bitmap)
                viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }
}