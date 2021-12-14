package com.curiousdev.composeplayground.dynamicreceipt.custom

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.*
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.platform.ComposeView
import com.curiousdev.composeplayground.dynamicreceipt.model.ReceiptResponse
import com.curiousdev.composeplayground.dynamicreceipt.screen.DisplayImage
import com.curiousdev.composeplayground.dynamicreceipt.screen.ReceiptPage

@SuppressLint("ViewConstructor")
@ExperimentalMaterialApi
class ReceiptLayout(ctx: Context ,private val image: Int, onBitmapCreated: (bitmap: Bitmap) -> Unit) : LinearLayoutCompat(ctx) {

    init {
        val height = 200
        val width = 400

        val view = ComposeView(ctx)
        view.visibility = View.GONE
        view.layoutParams = LayoutParams(width, height)


        this.addView(view)

        view.setContent {
            DisplayImage(image = image)
        }

        viewTreeObserver.addOnGlobalLayoutListener(object :
            OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val graphicUtils = GraphicUtils()
                val bitmap = graphicUtils.createBitmapFromView(view = view, width = width, height = height)
                bitmap.density = 160
                onBitmapCreated(bitmap)
                viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }
}