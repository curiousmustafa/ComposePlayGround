package com.curiousdev.composeplayground.dynamicreceipt.screen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.curiousdev.composeplayground.R
import com.curiousdev.composeplayground.dynamicreceipt.custom.ReceiptLayout
import com.curiousdev.composeplayground.dynamicreceipt.model.ReceiptResponse
import com.curiousdev.composeplayground.dynamicreceipt.utils.SunmiPrintHelper
import com.curiousdev.composeplayground.dynamicreceipt.viewmodel.ReceiptViewModel
import com.curiousdev.composeplayground.ui.theme.ComposePlayGroundTheme
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

@ExperimentalMaterialApi
@Composable
fun ReceiptPage(){
    var print by remember {
        mutableStateOf(false)
    }
    if (print) {
        MainUI(applicationContext = LocalContext.current)
    }
    ComposePlayGroundTheme {
        // A surface container using the 'background' color from the theme
        Button(
            onClick = {
                print = true
            }
        ) {
            Text(text = "Print")
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun MainUI(applicationContext: Context) {
    val jsonFileString = getJsonDataFromAsset(applicationContext, "response.json")
    val gson = Gson()
    val responseType = object : TypeToken<ReceiptResponse>() {}.type
    val response: ReceiptResponse = gson.fromJson(jsonFileString, responseType)

    val viewModel: ReceiptViewModel = viewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val helper = SunmiPrintHelper.getInstance()
        response.sort.forEach {
            val title = it.replace("_"," ",true).replaceFirstChar {char->
                char.uppercase()
            }
            when{
                it.equals("image",true) -> {
                    if (response.image != null) {
                        ReceiptLayout(applicationContext,R.drawable.android2) { logo->
                            helper.setAlign(1)
                            helper.printBitmap(logo,0)
                            helper.printText("\n",26f,true,false)
                        }
                    }
                }
                it.equals("qr",true) -> {
                    if (response.qr != null) {
                        helper.feedPaper()
                        helper.setAlign(1)
                        helper.printQr(response.qr,6,0)
                        helper.feedPaper()
                    }
                }
                it.equals("details",true) -> {
                    if (response.details != null) {
                        helper.setAlign(0)
                        val transactions = response.details.transactions!!
                        transactions.forEach {
                            it.apply {
                                helper.printText("Name : $name",26f,true,false)
                                helper.printText("\n",26f,true,false)
                                helper.printText("Service : $service",26f,true,false)
                                helper.printText("\n",26f,true,false)
                                helper.printText("Original Price : $originalPrice",26f,true,false)
                                helper.printText("\n",26f,true,false)
                                helper.printText("Discount : $discount",26f,true,false)
                                helper.printText("\n",26f,true,false)
                                helper.printText("Charges : $charges",26f,true,false)
                                helper.printText("\n",26f,true,false)
                                helper.printText("Quantity : $quantity",26f,true,false)
                                helper.printText("\n",26f,true,false)
                                helper.printText("Subtotal : $subtotal",26f,true,false)
                                helper.printText("\n",26f,true,false)
                                helper.printText("Worker : $worker",26f,true,false)
                                helper.printText("\n",26f,true,false)
                            }
                            helper.print3Line()

                        }
                        helper.print3Line()
                        helper.printText("Total Quantity : ${response.details.totalQty}",26f,true,false)
                        helper.printText("\n",26f,true,false)
                        helper.printText("Total Amount : ${response.details.totalAmount}",26f,true,false)
                        helper.printText("\n",26f,true,false)
                        helper.printText("Tax : ${response.details.tax}",26f,true,false)
                        helper.printText("\n\n",26f,true,false)
                    }
                }

                it.equals("merchant_name",true) -> {
                    if (response.merchantName != null) {
                        helper.setAlign(1)
                        helper.printText("${response.merchantName}",26f,true,false)
                        helper.printText("\n",26f,true,false)
                    }
                }
                it.startsWith("splitter",true) -> {
                    var splitter = "*".repeat(30)
                    helper.printText(splitter,26f,true,false)
                    helper.printText("\n",26f,true,false)
                }
                it.equals("date",true) -> {
                    if (response.date != null) {
                        helper.setAlign(0)
                        helper.printText("$title : ${response.date}",23f,false,false)
                        helper.printText("\n",26f,true,false)
                    }
                }
                it.equals("merchant_id",true) -> {
                    if (response.merchantId != null) {
                        helper.setAlign(0)
                        helper.printText("$title : ${response.merchantId}",23f,false,false)
                        helper.printText("\n",26f,true,false)
                    }
                }
                it.equals("terminal_id",true) -> {
                    if (response.terminalId != null) {
                        helper.setAlign(0)
                        helper.printText("$title : ${response.terminalId}",23f,false,false)
                        helper.printText("\n",26f,true,false)
                    }
                }
                it.equals("transaction_id",true) -> {
                    if (response.transactionId != null) {
                        helper.setAlign(0)
                        helper.printText("$title : ${response.transactionId}",23f,false,false)
                        helper.printText("\n",26f,true,false)
                    }
                }
                it.equals("voucher_no",true) -> {
                    if (response.voucherNo != null) {
                        helper.setAlign(0)
                        helper.printText("$title : ${response.voucherNo}",23f,false,false)
                        helper.printText("\n",26f,true,false)
                    }
                }
                it.equals("car_number",true) -> {
                    if (response.carNumber != null) {
                        helper.setAlign(0)
                        helper.printText("$title : ${response.carNumber}",23f,false,false)
                        helper.printText("\n",26f,true,false)
                    }
                }
                it.equals("customer_no",true) -> {
                    if (response.customerNo != null) {
                        helper.setAlign(0)
                        helper.printText("$title : ${response.customerNo}",23f,false,false)
                        helper.printText("\n",26f,true,false)
                    }
                }
                it.equals("header",true) -> {
                    if (response.header != null) {
                        helper.setAlign(1)
                        helper.printText("${response.header}",35f,true,false)
                        helper.printText("\n",26f,true,false)
                    }
                }
                it.equals("total",true) -> {
                    if (response.total != null) {
                        helper.setAlign(0)
                        helper.printText("$title : ${response.total}",35f,true,false)
                        helper.printText("\n",26f,true,false)
                    }
                }
                it.equals("address",true) -> {
                    if (response.address != null) {
                        helper.setAlign(0)
                        helper.printText("${response.address}",23f,false,false)
                        helper.printText("\n",26f,true,false)
                    }
                }
                it.equals("city",true) -> {
                    if (response.city != null) {
                        helper.setAlign(0)
                        helper.printText("$title : ${response.city}",23f,false,false)
                        helper.printText("\n",26f,true,false)
                    }
                }
                it.equals("country",true) -> {
                    if (response.country != null) {
                        helper.setAlign(0)
                        helper.printText("${response.country}",23f,false,false)
                        helper.printText("\n",26f,true,false)
                    }
                }
                it.equals("phone",true) -> {
                    if (response.phone != null) {
                        helper.setAlign(0)
                        helper.printText("Call us : ${response.phone}",23f,false,false)
                        helper.printText("\n",26f,true,false)
                    }
                }
                it.equals("bar_code",true) -> {
                    if (response.barCode != null) {
                        helper.setAlign(1)
                        helper.printBarCode(response.barCode,8,150,5,1)
                        helper.printText("\n",26f,true,false)
                        helper.feedPaper()
                    }
                }

            }
        }
    }
}

@Composable
fun DisplayBitmapWithReceipt(applicationContext: Context) {
    val viewModel: ReceiptViewModel = viewModel()


}


fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}