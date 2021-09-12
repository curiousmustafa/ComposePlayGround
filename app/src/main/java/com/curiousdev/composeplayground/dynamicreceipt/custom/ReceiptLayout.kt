package com.curiousdev.composeplayground.dynamicreceipt.custom

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.compose.ui.platform.ComposeView
import com.curiousdev.composeplayground.R
import com.curiousdev.composeplayground.dynamicreceipt.model.MyPair
import com.curiousdev.composeplayground.dynamicreceipt.model.MyTransaction
import com.curiousdev.composeplayground.dynamicreceipt.screen.ReceiptPage

class ReceiptLayout(ctx: Context,response: MutableList<MyPair>, onBitmapCreated: (bitmap: Bitmap) -> Unit) : LinearLayoutCompat(ctx) {

    init {
        val height = 670

        val view = ComposeView(ctx)
        view.visibility = View.GONE
        view.layoutParams = LayoutParams(width, height)
        this.addView(view)

        view.setContent {
            ReceiptPage(response)
        }

        viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val graphicUtils = GraphicUtils()
                val bitmap = graphicUtils.createBitmapFromView(view = view, width = width, height = height)
                onBitmapCreated(bitmap)
                viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }
}