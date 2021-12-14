package com.curiousdev.composeplayground.animatedSealedState.screen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.curiousdev.composeplayground.animatedSealedState.model.Product
import com.curiousdev.composeplayground.animatedSealedState.repo.DataRepo
import com.curiousdev.composeplayground.ui.theme.lightGray

val products = DataRepo.products
var scrollOffset = 1

@ExperimentalFoundationApi
@Composable
fun DrawerHomePage(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightGray),
        verticalArrangement = Arrangement.Center
    ) {
        Box {
            val scrollState = rememberLazyListState()
            val currentScrollOffset = scrollState.firstVisibleItemScrollOffset
            var scrollerTopOffset by remember { mutableStateOf(50.dp) }
            val animatedTopOffset by animateDpAsState(
                targetValue = scrollerTopOffset,
            )

            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 7.5.dp),
                state = scrollState,
                modifier = Modifier.offset(y = animatedTopOffset)
            ){
                items(products){ product->
                    GridItem(
                        product = product,
                        onProductClicked = {

                        }
                    )
                }
            }

            if (currentScrollOffset < scrollOffset){
                scrollerTopOffset = 50.dp
                Text(text = "All products",style = MaterialTheme.typography.h6,modifier = Modifier.padding(15.dp))
            } else {
                scrollerTopOffset = 0.dp
            }
            scrollOffset = currentScrollOffset - 10
        }
    }
}

@Composable
fun GridItem(modifier: Modifier = Modifier , product: Product , onProductClicked: ()-> Unit){
    Column(
        modifier = modifier
            .padding(7.5.dp)
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
            .clickable {
                onProductClicked()
            }
            .padding(10.dp),
    ) {
        Image(
            painter = painterResource(id = product.image),
            contentDescription = "item image",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(
                    RoundedCornerShape(15.dp)
                ),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = product.name,style = MaterialTheme.typography.body1,maxLines = 1)
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "$${product.price}",style = MaterialTheme.typography.body2,color = Color.Gray)
            Text(text = "${product.rate}",style = MaterialTheme.typography.body2,color = Color.Gray)
        }
    }
}