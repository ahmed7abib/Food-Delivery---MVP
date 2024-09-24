package com.th.group.fooddelivery.ui.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.th.group.fooddelivery.R
import com.th.group.fooddelivery.ui.presentation.Screens
import com.th.group.fooddelivery.ui.presentation.favorite.IconWithText
import com.th.group.fooddelivery.ui.presentation.restaurant.RestaurantsViewModel
import com.th.group.fooddelivery.ui.theme.Bink
import com.th.group.fooddelivery.ui.theme.Orange0
import com.th.group.fooddelivery.ui.theme.Orange1
import com.th.group.fooddelivery.utils.commonUi.HorizontalListView
import com.th.group.fooddelivery.utils.commonUi.VerticalListView
import kotlinx.coroutines.delay


@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel,
    restaurantsViewModel: RestaurantsViewModel,
) {

    var showSheet by remember { mutableStateOf(false) }

    var currentIndex by remember { mutableIntStateOf(0) }

    val addressList by homeViewModel.addressList.collectAsState()
    var currentAddress by remember { mutableStateOf(addressList.first()) }

    val categoryList by homeViewModel.categoryList.collectAsState()
    val promotionList by homeViewModel.promotionList.collectAsState()
    val restaurants by restaurantsViewModel.restaurants.collectAsState()

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            currentIndex = (currentIndex + 1) % promotionList.size
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                if (currentAddress.addressType == AddressType.HOME) {
                    Image(
                        painter = painterResource(id = R.drawable.home_icon),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.office_icon),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Row(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                        .clickable { showSheet = true }
                ) {

                    Text(
                        text = "${currentAddress.addressType.value},",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = "${currentAddress.blockNumber}. ${currentAddress.streetName}, ${currentAddress.city}",
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Image(
                        painter = painterResource(id = R.drawable.bottom_arrow_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(16.dp)
                            .align(Alignment.CenterVertically)
                    )
                }

                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clickable { navHostController.navigate(Screens.OrderScreen.route) }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.cart_img),
                        contentDescription = null,
                        modifier = Modifier
                            .size(28.dp)
                            .align(Alignment.BottomCenter)
                    )

                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .clip(CircleShape)
                            .background(color = Color.Red)
                            .align(Alignment.TopEnd)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

                CustomSlider(sliderContent = promotionList, currentIndex = currentIndex)

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "Restaurants",
                    color = Color.Black,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(30.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Categories",
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Orange1)
                            .padding(vertical = 2.dp, horizontal = 16.dp)
                            .clickable { seeAllCategoriesClick() }
                    ) {
                        Text(text = "See all", color = Orange0, fontSize = 12.sp)
                    }
                }

                HorizontalListView(
                    modifier = Modifier.padding(end = 4.dp, top = 20.dp),
                    items = categoryList
                ) {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(1.5.dp),
                        modifier = Modifier
                            .padding(start = 4.dp, end = 8.dp)
                            .height(180.dp)
                            .width(140.dp),
                        colors = CardColors(
                            Color.White, Color.White,
                            Color.White, Color.White
                        )
                    ) {
                        Column {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(120.dp)
                                    .background(Bink),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = it.image),
                                    contentDescription = null,
                                    contentScale = ContentScale.FillBounds,
                                    modifier = Modifier.size(105.dp)
                                )
                            }

                            Spacer(modifier = Modifier.weight(1f))

                            Text(
                                text = it.name,
                                fontSize = 14.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 10.dp)
                            )

                            Text(
                                text = "${it.placesNumbers} Places",
                                fontSize = 12.sp,
                                color = Color.Gray,
                                modifier = Modifier.padding(start = 10.dp, bottom = 4.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "All Restaurants",
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )

                VerticalListView(
                    modifier = Modifier
                        .padding(end = 4.dp, top = 20.dp)
                        .height(450.dp),
                    items = restaurants
                ) {
                    Column {
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            elevation = CardDefaults.cardElevation(1.5.dp),
                            modifier = Modifier
                                .padding(start = 4.dp, end = 8.dp)
                                .fillMaxWidth()
                                .height(250.dp)
                                .clickable {
                                    navHostController.currentBackStackEntry?.savedStateHandle?.set(
                                        "restaurantData",
                                        it
                                    )
                                    navHostController.navigate(Screens.RestaurantDetailsScreen.route)
                                },
                            colors = CardColors(
                                Color.White, Color.White,
                                Color.White, Color.White
                            )
                        ) {
                            Column {
                                Image(
                                    painter = painterResource(id = it.image),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(155.dp),
                                    contentScale = ContentScale.FillBounds
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = it.name,
                                    fontSize = 16.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(start = 10.dp)
                                )

                                Spacer(modifier = Modifier.height(2.dp))

                                Text(
                                    text = it.description,
                                    fontSize = 12.sp,
                                    color = Color.Gray,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.padding(start = 10.dp)
                                )

                                Spacer(modifier = Modifier.weight(1f))

                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .padding(
                                            start = 10.dp,
                                            end = 10.dp,
                                            bottom = 10.dp
                                        )
                                        .fillMaxWidth()
                                ) {
                                    IconWithText(
                                        iconRes = R.drawable.ic_delivery,
                                        text = "$${it.deliveryFees}"
                                    )

                                    IconWithText(
                                        iconRes = R.drawable.ic_prep_time,
                                        text = "${it.deliveryTime} min"
                                    )

                                    IconWithText(
                                        iconRes = R.drawable.ic_rating,
                                        text = it.rating.toString()
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }

        if (showSheet) {
            AddressDetailsBottomSheet(
                addressList,
                onDismiss = {
                    showSheet = false
                    if (it != null) currentAddress = it
                },
            )
        }
    }
}

fun seeAllCategoriesClick() {}

@Composable
fun CustomSlider(sliderContent: List<PromotionDto>, currentIndex: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
    ) {
        SliderItem(item = sliderContent[currentIndex])

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            sliderContent.forEachIndexed { index, _ ->
                SliderIndicator(active = index == currentIndex)
                if (index < sliderContent.size - 1) Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}

@Composable
fun SliderItem(item: PromotionDto) {

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Orange0)
            .fillMaxWidth()
            .height(190.dp)
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .weight(1.5f)
            ) {
                Text(
                    text = item.promotionText,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 16.sp,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { promotionClick() },
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {
                    Text(
                        text = item.actionText,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(vertical = 6.dp)
                    )
                }
            }

            Image(
                painter = painterResource(id = item.promotionImage),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.8f)
                    .padding(bottom = 8.dp, top = 2.dp)
            )
        }
    }
}

@Composable
fun SliderIndicator(active: Boolean) {
    val color = if (active) Orange0 else Color.Gray

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(color)
            .size(10.dp)
    )
}

fun promotionClick() {

}