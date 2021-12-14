package com.curiousdev.composeplayground.sealed

sealed class MainRoute(val route: String, val title: String){
    object Guider: MainRoute("guider","Guider")
    object Receipt: MainRoute("receipt","Dynamic Receipt")
    object AnimatedDrawerExample: MainRoute("animatedDrawer","Animated Drawer")
}
