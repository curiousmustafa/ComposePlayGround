package com.curiousdev.composeplayground

import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.curiousdev.composeplayground.dynamicreceipt.custom.ReceiptLayout
import com.curiousdev.composeplayground.dynamicreceipt.model.MyPair
import com.curiousdev.composeplayground.dynamicreceipt.model.MyTransaction
import com.curiousdev.composeplayground.ui.theme.ComposePlayGroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlayGroundTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainUI(onBitmapCreated = {
                        if (it != null) {
                            
                        }
                    })
                }
            }
        }
    }
}

@Composable
fun MainUI(onBitmapCreated: (bitmap: Bitmap?) -> Unit) {
    val response = mutableListOf(
        MyPair("image", R.drawable.ic_constructor),
        MyPair("splitter", "*"),
        MyPair("merchant_name", "Tom & Jerry"),
        MyPair("time", "10:34:28 AM "),
        MyPair("merchant_id", "34389838439"),
        MyPair("terminal_id", "34389838439"),
        MyPair("ref_number", "34389838439"),
        MyPair("custom_no", "34389838439"),
        MyPair("address", "34389838439"),
        MyPair("splitter", "*"),
        MyPair("token_number", "73"),
        MyPair("splitter", "*"),
        MyPair("transactions", listOf(
            MyTransaction(item = "T shirt",qty = 2,10.00,20.00),
            MyTransaction(item = "Skirt",qty = 1,6.00,6.00),
            MyTransaction(item = "Shoes",qty = 5,20.00,100.00),
            MyTransaction(item = "Bag",qty = 4,3.00,12.00),
        )
        ),
        MyPair("splitter", "-"),
        MyPair("overall_total", 150.00),
        MyPair("splitter", "-"),
        MyPair("city", "Dubai"),
        MyPair("snap_qr", R.drawable.ic_qr),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AndroidView(
            factory = { context ->
                val receipt = ReceiptLayout(ctx = context,response) { bitmap ->
                    onBitmapCreated(bitmap)
                }
                receipt
            }
        )
    }
}
