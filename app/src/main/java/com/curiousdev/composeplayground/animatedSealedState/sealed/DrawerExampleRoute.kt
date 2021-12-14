package com.curiousdev.composeplayground.animatedSealedState.sealed

import com.curiousdev.composeplayground.R

sealed class DrawerExampleRoute (val route: String, val title: String, val icon: Int = 0){
    object Holder: DrawerExampleRoute("holder","Holder")
    object Home: DrawerExampleRoute("home","Home",R.drawable.ic_home)
    object Favorite: DrawerExampleRoute(route = "favorite","Favorite",R.drawable.ic_fav)
    object History: DrawerExampleRoute(route = "history",title = "History",R.drawable.ic_history)
    object Configure: DrawerExampleRoute(route = "configure",title = "Configuration",R.drawable.ic_configure)
    object Profile: DrawerExampleRoute(route = "profile",title = "Profile")
    object Search: DrawerExampleRoute(route = "search",title = "Search")
}