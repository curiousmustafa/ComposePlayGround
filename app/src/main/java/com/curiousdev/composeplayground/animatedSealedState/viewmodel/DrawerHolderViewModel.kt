package com.curiousdev.composeplayground.animatedSealedState.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.curiousdev.composeplayground.animatedSealedState.sealed.DrawerExampleRoute

class DrawerHolderViewModel : ViewModel(){
    private var _currentDrawerExampleRoute: MutableLiveData<DrawerExampleRoute> = MutableLiveData(DrawerExampleRoute.Home)
    val currentDrawerExampleRoute: LiveData<DrawerExampleRoute> = _currentDrawerExampleRoute

    fun setCurrentScreen(drawerExampleRoute: DrawerExampleRoute){
        _currentDrawerExampleRoute.value = drawerExampleRoute
    }

}