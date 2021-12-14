package com.curiousdev.composeplayground.dynamicreceipt.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

import android.telecom.Call.Details


data class ReceiptResponse(

    @SerializedName("sort")
    @Expose
    val sort: List<String>,

    @SerializedName("image")
    @Expose
     val image: String? = null,

    @SerializedName("merchant_name")
    @Expose
     val merchantName: String? = null,

    @SerializedName("date")
    @Expose
     val date: String? = null,

    @SerializedName("merchant_id")
    @Expose
     val merchantId: String? = null,

    @SerializedName("terminal_id")
    @Expose
     val terminalId: String? = null,

    @SerializedName("transaction_id")
    @Expose
     val transactionId: String? = null,

    @SerializedName("voucher_no")
    @Expose
     val voucherNo: String? = null,

    @SerializedName("car_number")
    @Expose
     val carNumber: String? = null,

    @SerializedName("customer_no")
    @Expose
     val customerNo: String? = null,

    @SerializedName("splitter_1")
    @Expose
     val splitter1: String? = null,

    @SerializedName("header")
    @Expose
     val header: String? = null,

    @SerializedName("splitter_2")
    @Expose
     val splitter2: String? = null,

    @SerializedName("details")
    @Expose
     val details: ReceiptDetails? = null,

    @SerializedName("total")
    @Expose
     val total: Double? = null,

    @SerializedName("address")
    @Expose
     val address: String? = null,

    @SerializedName("city")
    @Expose
     val city: String? = null,

    @SerializedName("country")
    @Expose
     val country: String? = null,

    @SerializedName("phone")
    @Expose
     val phone: String? = null,

    @SerializedName("splitter_3")
    @Expose
     val splitter3: String? = null,

    @SerializedName("splitter_4")
    @Expose
     val splitter4: String? = null,

    @SerializedName("bar_code")
    @Expose
     val barCode: String? = null,
    @SerializedName("qr")
    @Expose
     val qr: String? = null,

)