package com.curiousdev.composeplayground.dynamicreceipt.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReceiptViewModel : ViewModel(){
    private var _onLogoCreated = MutableLiveData<Bitmap?>(null)
    var onLogoGenerated: LiveData<Bitmap?> = _onLogoCreated
    private var _onBarCodeCreated = MutableLiveData<Bitmap?>(null)
    var onBarCodeGenerated: LiveData<Bitmap?> = _onBarCodeCreated
    private var _onQrCodeCreated = MutableLiveData<Bitmap?>(null)
    var onQrCodeGenerated: LiveData<Bitmap?> = _onQrCodeCreated

    fun logoCreated(bitmap: Bitmap?) {
        _onLogoCreated.value = bitmap
    }
    fun barCodeCreated(bitmap: Bitmap?) {
        _onBarCodeCreated.value = bitmap
    }
    fun qrCreated(bitmap: Bitmap?) {
        _onQrCodeCreated.value = bitmap
    }

}