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
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.curiousdev.composeplayground.R
import com.curiousdev.composeplayground.dynamicreceipt.model.MyPair
import com.curiousdev.composeplayground.dynamicreceipt.model.MyTransaction

@Composable
fun ReceiptPage(response: MutableList<MyPair> = mutableListOf()){
    Column(modifier = Modifier
        .background(Color.White)
        .fillMaxSize()
        .padding(horizontal = 15.dp, vertical = 30.dp)){
        response.forEach { (key, value) ->
            when {
                key.equals("image",true) -> {
                    DisplayImage(image = value as Int)
                }
                key.equals("snap_qr",true) -> {
                    DisplayImage(image = value as Int)
                }
                key.equals("transactions",true) -> {
                    TransactionDetails(transactions = value as MutableList<MyTransaction>)
                }

                key.equals("merchant_name",true) -> {
                    DisplayMerchantName(name = value.toString())
                }
                key.equals("splitter",true) -> {
                    DisplaySeparator(pattern = value.toString())
                }
                else -> {
                    var title = key.replace("_"," ",true)
                    title = title.capitalize(Locale("en"))
                    DisplayText(
                        title =title,
                        value = value.toString()
                    )
                }
            }
        }
    }

}

@Composable
fun DisplayText(title: String, value: String){
    Text(
        text = buildAnnotatedString {
            withStyle(MaterialTheme.typography.body2.toSpanStyle()){
                append("$title: ")
            }
            withStyle(MaterialTheme.typography.caption.toSpanStyle()){
                append(value)
            }
        }
    )
}

@Composable
fun DisplayMerchantName(name: String){
    Text(
        text = name,
        style = MaterialTheme.typography.h6,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
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
                .size(80.dp)
                .align(Alignment.Center)
                .padding(vertical = 10.dp),
            colorFilter = ColorFilter.tint(Color.Black),
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
        style = MaterialTheme.typography.h6
    )
}

@Composable
fun TransactionDetails(transactions: MutableList<MyTransaction>){
    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = "Item",style = MaterialTheme.typography.h6)
            Text(text = "Qty",style = MaterialTheme.typography.h6)
            Text(text = "Price",style = MaterialTheme.typography.h6)
            Text(text = "Total",style = MaterialTheme.typography.h6)
        }
        LazyColumn{
            items(transactions){transaction->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(text = transaction.item,style = MaterialTheme.typography.body1)
                    Text(text = transaction.qty.toString(),style = MaterialTheme.typography.body1)
                    Text(text = transaction.price.toString(),style = MaterialTheme.typography.body1)
                    Text(text = transaction.total.toString(),style = MaterialTheme.typography.body1)
                }
            }
        }
    }
}