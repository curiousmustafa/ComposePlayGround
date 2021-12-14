package com.curiousdev.composeplayground.dynamicreceipt.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.curiousdev.composeplayground.dynamicreceipt.model.ReceiptResponse
import com.curiousdev.composeplayground.R
import com.curiousdev.composeplayground.dynamicreceipt.model.ReceiptDetails

@Composable
fun ReceiptPage(response: ReceiptResponse = ReceiptResponse(sort = mutableListOf())){
    Column(modifier = Modifier
        .background(Color.White)
        .fillMaxSize()
        .padding(horizontal = 15.dp, vertical = 30.dp)){
        response.sort.forEach {
            val title = it.replace("_"," ",true).replaceFirstChar {char->
                char.uppercase()
            }
            when{
                it.equals("image",true) -> {
                    if (response.image != null) {
                        DisplayImage(image = R.drawable.android)
                    } 
                }
                it.equals("snap_qr",true) -> {
                    DisplayImage(image = R.drawable.android)
                }
                it.equals("details",true) -> {
                    if (response.details != null) {
                        TransactionDetails(details = response.details)
                    }
                }

                it.equals("merchant_name",true) -> {
                    if (response.merchantName != null) {
                        DisplayMerchantName(name = response.merchantName)
                    }
                }
                it.startsWith("splitter",true) -> {
                    DisplaySeparator(pattern = "*")
                }
                it.equals("date",true) -> {
                    if (response.date != null) {
                        DisplayText(
                            title =title,
                            value = response.date
                        )
                    }
                }
                it.equals("merchant_id",true) -> {
                    if (response.merchantId != null) {
                        DisplayText(
                            title =title,
                            value = response.merchantId
                        )
                    }
                }
                it.equals("terminal_id",true) -> {
                    if (response.terminalId != null) {
                        DisplayText(
                            title =title,
                            value = response.terminalId
                        )
                    }
                }
                it.equals("transaction_id",true) -> {
                    if (response.transactionId != null) {
                        DisplayText(
                            title =title,
                            value = response.transactionId
                        )
                    }
                }
                it.equals("voucher_no",true) -> {
                    if (response.voucherNo != null) {
                        DisplayText(
                            title =title,
                            value = response.voucherNo
                        )
                    }
                }
                it.equals("car_number",true) -> {
                    if (response.carNumber != null) {
                        DisplayText(
                            title =title,
                            value = response.carNumber
                        )
                    }
                }
                it.equals("customer_no",true) -> {
                    if (response.customerNo != null) {
                        DisplayText(
                            title =title,
                            value = response.customerNo
                        )
                    }
                }
                it.equals("header",true) -> {
                    if (response.header != null) {
                        DisplayHeader(
                            name = response.header
                        )
                    }
                }
                it.equals("total",true) -> {
                    if (response.total != null) {
                        DisplayText(
                            title =title,
                            value = response.total.toString()
                        )
                    }
                }
                it.equals("address",true) -> {
                    if (response.address != null) {
                        DisplayText(
                            title =title,
                            value = response.address
                        )
                    }
                }
                it.equals("city",true) -> {
                    if (response.city != null) {
                        DisplayText(
                            title =title,
                            value = response.city
                        )
                    }
                }
                it.equals("country",true) -> {
                    if (response.country != null) {
                        DisplayText(
                            title =title,
                            value = response.country
                        )
                    }
                }
                it.equals("phone",true) -> {
                    if (response.phone != null) {
                        DisplayText(
                            title =title,
                            value = response.phone
                        )
                    }
                }
                it.equals("bar_code",true) -> {
                    if (response.barCode != null) {
                        ShowBarCode(value = response.barCode)
                    }
                }

            }
        }
    }

}

@Composable
fun ShowBarCode(value: String) {
    Image(
        painter = painterResource(id = R.drawable.barcode),
        contentDescription = "barcode",
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
    )
}

@Composable
fun DisplayText(title: String, value: String){
    Text(
        text = buildAnnotatedString {
            withStyle(MaterialTheme.typography.h6.toSpanStyle()){
                append("$title: ")
            }
            withStyle(MaterialTheme.typography.h6.toSpanStyle()){
                append(value)
            }
        }
    )
}

@Composable
fun DisplayMerchantName(name: String){
    Text(
        text = name,
        style = MaterialTheme.typography.h4,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
fun DisplayHeader(name: String){
    Text(
        text = name,
        style = MaterialTheme.typography.h3,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
fun DisplayImage(image: Int){
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "image",
            modifier = Modifier
                .size(250.dp)
                .align(Alignment.Center)
                .padding(vertical = 10.dp),
        )
    }
}

@Composable
fun DisplaySeparator(pattern: String){
    Text(
        text = pattern.repeat(40),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        style = MaterialTheme.typography.h4
    )
}

@Composable
fun TransactionDetails(details: ReceiptDetails){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        LazyColumn(){
            items(details.transactions!!){t->
                Column {
                    if (t.name!= null) {
                        DisplayText(title = "Name", value = t.name)
                    }
                    if (t.service!= null) {
                        DisplayText(title = "Service", value = t.service)
                    }
                    if (t.originalPrice!= null) {
                        DisplayText(title = "Original Price", value = t.originalPrice.toString())
                    }
                    if (t.discount!= null) {
                        DisplayText(title = "Discount", value = t.discount.toString())
                    }
                    if (t.charges!= null) {
                        DisplayText(title = "Charges", value = t.charges.toString())
                    }
                    if (t.quantity!= null) {
                        DisplayText(title = "Quantity", value = t.quantity.toString())
                    }
                    if (t.subtotal!= null) {
                        DisplayText(title = "SubTotal", value = t.subtotal.toString())
                    }
                    if (t.worker!= null) {
                        DisplayText(title = "Worker", value = t.worker)
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(15.dp))
        if (details.totalQty != null) {
            DisplayText(title = "Total QTY", value = details.totalQty.toString())
        }
        Spacer(modifier = Modifier.height(15.dp))
        if (details.totalQty != null) {
            DisplayText(title = "Total Amount", value = "${details.totalAmount.toString()} AED")
        }
        if (details.totalQty != null) {
            DisplayText(title = "Total QTY", value = "${details.tax.toString()} AED")
        }

    }
}