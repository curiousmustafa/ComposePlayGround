package com.curiousdev.composeplayground.animatedSealedState.screen

import android.graphics.drawable.Icon
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.curiousdev.composeplayground.R
import com.curiousdev.composeplayground.animatedSealedState.model.User
import com.curiousdev.composeplayground.animatedSealedState.sealed.DrawerExampleRoute
import com.curiousdev.composeplayground.animatedSealedState.viewmodel.DrawerHolderViewModel
import com.curiousdev.composeplayground.dynamicreceipt.screen.ReceiptPage
import com.curiousdev.composeplayground.mainScreens
import com.curiousdev.composeplayground.sealed.MainRoute
import com.curiousdev.composeplayground.ui.theme.ComposePlayGroundTheme
import com.curiousdev.composeplayground.ui.theme.brown
import com.curiousdev.composeplayground.ui.theme.lightBrown
import com.curiousdev.composeplayground.ui.theme.lightGray
import kotlinx.coroutines.launch


val user = User(
    name = "Mustafa Ibrahim",
    gender = User.Gender.Male,
    email = "mi0117634@gmail.com",
    profile = R.drawable.profile
)

val drawerScreens = listOf(
    DrawerExampleRoute.Home,
    DrawerExampleRoute.Favorite,
    DrawerExampleRoute.History,
    DrawerExampleRoute.Configure
)

val startDestination = DrawerExampleRoute.Home

var width = 0f
var height = 0f
@ExperimentalFoundationApi
@Composable
fun AnimatedScreenHolder(
    viewModel: DrawerHolderViewModel = viewModel()
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(lightGray)
    ){
        // get width and height of the parent
        height = LocalContext.current.resources.displayMetrics.heightPixels / LocalDensity.current.density
        width = LocalContext.current.resources.displayMetrics.widthPixels / LocalDensity.current.density
        val drawerWidth = (width / 1.5).dp

        val currentScreen by viewModel.currentDrawerExampleRoute.observeAsState()
        val scope = rememberCoroutineScope()
        val scaffoldState = rememberScaffoldState()
        val navController = rememberNavController()
        var topBarTitle by remember{ mutableStateOf(startDestination.title) }
        var showTopBar by remember { mutableStateOf(true) }
        var drawerState by remember { mutableStateOf(DrawerValue.Closed) }
        var contentOffset by remember { mutableStateOf(0.dp) }
        var drawerOffset by remember { mutableStateOf(-drawerWidth) }

        val drawerWidthAnimation by animateDpAsState(
            targetValue = contentOffset,
            animationSpec = SpringSpec(
                dampingRatio = Spring.DampingRatioMediumBouncy,
            )
        )

        Column(modifier = Modifier
            .offset(x = drawerWidthAnimation)
            .fillMaxSize()
            .background(lightGray)
        ) {
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    if (showTopBar) {
                        AnimatedTopBar(
                            icon = if(drawerState == DrawerValue.Closed) Icons.Rounded.Menu else Icons.Rounded.Close,
                            title = topBarTitle,
                            onDrawerClicked = {
                                drawerState = if (drawerState == DrawerValue.Open)  DrawerValue.Closed  else DrawerValue.Open
                            },
                            onProfileClicked = {
                                navController.navigate(DrawerExampleRoute.Profile.route)
                            },
                            onSearchClicked = {
                                navController.navigate(DrawerExampleRoute.Search.route)
                            }
                        )
                    }
                }
            ) {
                NavHost(navController = navController, startDestination = DrawerExampleRoute.Home.route){
                    composable(DrawerExampleRoute.Home.route){
                        showTopBar = true
                        topBarTitle = DrawerExampleRoute.Home.title
                        DrawerHomePage()
                    }
                    composable(DrawerExampleRoute.Favorite.route){
                        showTopBar = true
                        topBarTitle = DrawerExampleRoute.Favorite.title
                        DrawerFavoritePage()
                    }
                    composable(DrawerExampleRoute.History.route){
                        showTopBar = true
                        topBarTitle = DrawerExampleRoute.History.title
                        DrawerHistoryPage()
                    }
                    composable(DrawerExampleRoute.Configure.route){
                        showTopBar = true
                        topBarTitle = DrawerExampleRoute.Configure.title
                        DrawerSettingPage()
                    }
                    composable(DrawerExampleRoute.Profile.route){
                        showTopBar = false
                        topBarTitle = DrawerExampleRoute.Profile.title
                        DrawerProfilePage()
                    }
                    composable(DrawerExampleRoute.Search.route){
                        showTopBar = false
                        DrawerSearchPage()
                    }
                }
            }

        }

        contentOffset = if (drawerState == DrawerValue.Open) drawerWidth else 0.dp
        drawerOffset = if (drawerState == DrawerValue.Open) 0.dp else -drawerWidth

        DrawerContent(
            drawerOffset = drawerOffset,
            currentDrawerExampleRoute = currentScreen?: startDestination,
            onDrawerItemSelected = {screen ->
                // first we had to close the drawer
                contentOffset = 0.dp
                drawerState = DrawerValue.Closed
                // then we should navigate to the selected destination if it's not the current one
                if (screen != currentScreen) {
                    viewModel.setCurrentScreen(drawerExampleRoute = screen)
                    navController.navigate(screen.route){
                        popUpTo(startDestination.route)
                        launchSingleTop = true
                    }
                }
            },
            onHeaderClicked = {
                // first we had to close the drawer
                contentOffset = 0.dp
                drawerState = DrawerValue.Closed
                val screen = DrawerExampleRoute.Profile
                viewModel.setCurrentScreen(drawerExampleRoute = screen)
                navController.navigate(screen.route){
                    popUpTo(startDestination.route)
                    launchSingleTop = true
                }
            }
        )
    }

}

@Composable
fun DrawerContent(
    drawerOffset: Dp ,
    currentDrawerExampleRoute: DrawerExampleRoute = startDestination,
    onDrawerItemSelected: (drawerExampleRoute: DrawerExampleRoute) -> Unit = {},
    onHeaderClicked: () -> Unit = {}
){
    val drawerWidth = (width / 1.5).dp
    val drawerWidthAnimation by animateDpAsState(
        targetValue = drawerOffset,
        animationSpec = SpringSpec(
            dampingRatio = Spring.DampingRatioMediumBouncy,
        )
    )
    Column(
        modifier = Modifier
            .offset(x = drawerWidthAnimation)
            .size(drawerWidth, height.dp)
            .background(Color.White)
    ){
        DrawerHeader(
            onHeaderClicked = {
                onHeaderClicked()
            }
        )
        Spacer(modifier = Modifier.height(30.dp))
        LazyColumn(){
            items(drawerScreens){screen->
                DrawerItem(
                    drawerExampleRoute = screen,
                    current = currentDrawerExampleRoute == screen,
                    onDrawerItemClicked = {
                        onDrawerItemSelected(screen)
                    }
                )
            }
        }
    }
}

@Composable
fun DrawerHeader(
    onHeaderClicked: ()-> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(brown)
            .clickable {
                onHeaderClicked()
            }
            .padding(20.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start
    ){
        Image(
            painter = painterResource(id = user.profile),
            contentDescription = "profile",
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .aspectRatio(1f)
                .border(width = 2.dp, shape = CircleShape, color = lightBrown)
                .padding(6.dp)
                .clip(
                    CircleShape
                )
        )
        Spacer(modifier = Modifier.width(30.dp))
        Column {
            Text(
                text = user.name,
                style = MaterialTheme.typography.body1,
                color = Color.LightGray
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = user.email,
                style = MaterialTheme.typography.subtitle2,
                color = Color.LightGray,
                fontSize = 10.sp
            )
        }
    }
}

@Composable
fun DrawerItem(
    drawerExampleRoute: DrawerExampleRoute = startDestination,
    current: Boolean = true,
    onDrawerItemClicked: ()-> Unit = {},
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .clip(
                RoundedCornerShape(15.dp)
            )
            .background(
                if (current) brown else Color.Transparent
            )
            .clickable {
                onDrawerItemClicked()
            }
            .padding(horizontal = 20.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = drawerExampleRoute.icon),
            contentDescription = "icon",
            modifier = Modifier
                .size(30.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(lightBrown)
                .padding(6.dp)
        )
        Spacer(modifier = Modifier.width(30.dp))
        Text(text = drawerExampleRoute.title, style = MaterialTheme.typography.body2,color = Color.Black)
    }
}

@Composable
fun AnimatedTopBar(
    title: String,
    icon: ImageVector,
    onProfileClicked: () -> Unit,
    onSearchClicked: () -> Unit,
    onDrawerClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(brown)
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "menu",
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .background(lightBrown)
                .clickable {
                    onDrawerClicked()
                }
                .padding(5.dp),
            tint = Color.Black
        )
        Text(
            text = title,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(15.dp),
            color = Color.Black
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = user.profile),
            contentDescription = "profile",
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .size(36.dp)
                .border(width = 2.dp, shape = CircleShape, color = lightBrown)
                .padding(2.dp)
                .clip(CircleShape)
                .background(lightBrown)
                .shadow(elevation = 2.dp, shape = CircleShape)
                .clickable {
                    onProfileClicked()
                },
            contentScale = ContentScale.Crop
        )
        Icon(
            imageVector = Icons.Rounded.Search,
            contentDescription = "search",
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(lightBrown)
                .clickable {
                    onSearchClicked()
                }
                .padding(5.dp),
            tint = Color.Black
        )
    }
}
