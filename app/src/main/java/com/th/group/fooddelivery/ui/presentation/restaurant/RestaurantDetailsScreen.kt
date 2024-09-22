package com.th.group.fooddelivery.ui.presentation.restaurant

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.th.group.fooddelivery.R
import com.th.group.fooddelivery.ui.presentation.DetailsScreens


@Composable
fun RestaurantDetailsScreen(navHostController: NavHostController) {

    val restaurantData =
        navHostController.previousBackStackEntry?.savedStateHandle?.get<Restaurant>("restaurantData")
            ?: Restaurant(
                -1,
                "",
                "",
                0,
                0.0,
                "",
                0.0,
                "",
                0,
                null
            )

    if (restaurantData.id != -1) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item { header(restaurantData.image) }
            item {
                body(BodyData(restaurantData.name,
                    restaurantData.description,
                    listOf(
                        Pair(if (restaurantData.rating > 4.0) buildAnnotatedString {
                            append("Excellent ${restaurantData.rating}")
                        } else buildAnnotatedString {
                            append("Good ${restaurantData.rating}")
                        }, R.drawable.ic_rating
                        ),
                        Pair(
                            buildAnnotatedString {
                                append("Delivery in ${restaurantData.deliveryTime} min to\n")
                                withStyle(style = SpanStyle(color = Color.Gray)) {
                                    append(restaurantData.address)
                                }
                            }, R.drawable.ic_delivery
                        ),
                        Pair(
                            buildAnnotatedString { append(restaurantData.operatingTime) },
                            R.drawable.baseline_access_time_filled_24
                        ),

                        Pair(
                            buildAnnotatedString { append("$${restaurantData.deliveryFees}") },
                            R.drawable.ic_payment
                        ),
                    )
                )
                )
            }
            item { restaurantData.menu?.let { footer(it, navHostController) } }

        }
    }

}


@Composable
fun header(imageRes: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        IconButton(
            onClick = { /* TODO: Handle back action */ },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart)
                .background(Color.DarkGray.copy(alpha = 0.8F), CircleShape)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "Back",
                tint = Color.White
            )
        }
    }
}

@Composable
fun body(data: BodyData) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Text(
                text = data.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = { /* TODO: Handle like action */ }) {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = "Like",
                    tint = Color.Red
                )
            }
        }
        Text(
            text = data.description, fontSize = 16.sp, modifier = Modifier.padding(bottom = 8.dp)
        )
        RowOfIcons(data.iconsWithText)
    }
}

@Composable
fun RowOfIcons(iconsWithText: List<Pair<AnnotatedString, Int>>) {
    iconsWithText.forEach { (text, iconRes) ->
        IconWithText(
            text = text, iconRes = iconRes
        )
    }
}


@Composable
fun IconWithText(iconRes: Int, text: AnnotatedString) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(top = 8.dp)
    ) {
        Icon(
            tint = colorResource(id = R.color.teal_200),
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, fontSize = 16.sp, fontWeight = FontWeight.W400, color = Color.DarkGray)
    }
}

@Composable
fun footer(
    menu: List<Pair<String, List<Dish>>>,
    navHostController: NavHostController,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        menu.forEach { category ->
            Text(
                text = category.first,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            category.second.forEach { dish: Dish ->
                MenuItem(dish, navHostController)
                divider(1, Color.LightGray)
            }
        }
    }
}

@Composable
fun divider(thickness: Int, color: Color) {
    HorizontalDivider(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp),
        thickness = thickness.dp,
        color = color
    )
}


@Composable
fun MenuItem(
    dish: Dish,
    navHostController: NavHostController,
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        .clickable {
            navHostController.currentBackStackEntry?.savedStateHandle?.set(
                "data",
                dish
            )
            navHostController.navigate(DetailsScreens.MenuItemDetailsScreen.route)
        }) {
        Column(
            modifier = Modifier
                .weight(2f)
                .padding(end = 8.dp)
        ) {
            Text(
                color = Color.DarkGray,
                text = dish.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                color = Color.DarkGray.copy(alpha = 0.8F),
                text = dish.ingredients,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            priceTag(dish.price, dish.discount)

        }

        Image(
            painter = painterResource(id = dish.image),
            contentDescription = dish.title,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .weight(1f)
                .height(120.dp)
                .background(Color.LightGray, RoundedCornerShape(8.dp))
                .padding(8.dp)
        )
    }
}

@Composable
fun priceTag(price: Double, discount: Double?) {
    Row {
        Text(
            color = Color.Red,
            text = "$${price}",
            fontSize = 16.sp,
            modifier = Modifier.padding(end = 8.dp)
        )
        discount?.let {
            Text(
                text = "$$it",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.LineThrough,
            )
        }
    }
}

data class BodyData(
    val title: String,
    val description: String,
    val iconsWithText: List<Pair<AnnotatedString, Int>>,
)
