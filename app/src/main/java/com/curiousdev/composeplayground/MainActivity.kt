package com.curiousdev.composeplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.curiousdev.composeplayground.animatedSealedState.screen.AnimatedScreenHolder
import com.curiousdev.composeplayground.animatedSealedState.sealed.DrawerExampleRoute
import com.curiousdev.composeplayground.dynamicreceipt.screen.ReceiptPage
import com.curiousdev.composeplayground.sealed.MainRoute
import com.curiousdev.composeplayground.ui.theme.ComposePlayGroundTheme
import com.curiousdev.composeplayground.ui.theme.brown

val mainScreens = listOf(
    MainRoute.Receipt,
    MainRoute.AnimatedDrawerExample
)
val startDestination = MainRoute.Guider

class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ComposePlayGroundTheme {
                NavHost(navController = navController, startDestination = startDestination.route) {
                    composable(MainRoute.Guider.route) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.LightGray),
                            contentPadding = PaddingValues(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            items(mainScreens) { screen ->
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(15.dp))
                                        .clickable {
                                            navController.navigate(screen.route)
                                        }
                                        .background(brown)
                                        .padding(horizontal = 25.dp, vertical = 15.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = screen.title,
                                        style = MaterialTheme.typography.body1,
                                    )
                                }
                                Spacer(modifier = Modifier.height(15.dp))
                            }
                        }
                    }
                    composable(MainRoute.Receipt.route) {
                        ReceiptPage()
                    }
                    navigation(
                        startDestination = DrawerExampleRoute.Holder.route,
                        route = MainRoute.AnimatedDrawerExample.route
                    ) {
                        composable(DrawerExampleRoute.Holder.route) {
                            AnimatedScreenHolder()
                        }
                    }
                }
            }
        }
    }
}
