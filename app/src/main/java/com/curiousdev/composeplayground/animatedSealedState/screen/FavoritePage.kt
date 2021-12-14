package com.curiousdev.composeplayground.animatedSealedState.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.curiousdev.composeplayground.ui.theme.lightGray

@Composable
fun DrawerFavoritePage(){
    Column(
        modifier = Modifier.fillMaxSize().background(lightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "This is the favorite page !")
    }
}