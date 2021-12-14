package com.curiousdev.composeplayground.dynamicreceipt.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import android.view.SurfaceControl.Transaction





data class ReceiptDetails(

    @SerializedName("total_qty")
    @Expose
    val totalQty: Int? = null,

    @SerializedName("total_amount")
    @Expose
    val totalAmount: Double? = null,

    @SerializedName("tax")
    @Expose
    val tax: Double? = null,

    @SerializedName("transactions")
    @Expose
    val transactions: List<MyTransaction>? = null
)