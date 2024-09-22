package com.th.group.fooddelivery.ui.presentation.favorite

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.th.group.fooddelivery.R
import com.th.group.fooddelivery.ui.theme.FoodDeliveryTheme
import com.th.group.fooddelivery.utils.commonUi.VerticalListView

@Composable
fun FavoriteScreen(viewModel: FavoriteViewModel) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val favoriteFoodItems by viewModel.favoriteFoodItems.collectAsState()
    val favoriteRestaurantItems by viewModel.favoriteRestaurantItems.collectAsState()

    Column {
        SearchBar()
        FavoriteTabs(selectedTab) { selectedTab = it }
        when (selectedTab) {
            0 -> VerticalListView(
                modifier = Modifier.padding(16.dp),
                favoriteFoodItems
            ) { item -> FavoriteFoodItemCard(item) }

            1 -> VerticalListView(
                modifier = Modifier.padding(16.dp),
                favoriteRestaurantItems
            ) { item ->
                FavoriteRestaurantItemCard(
                    item
                )
            }
        }
    }
}

@Composable
fun SearchBar() {
    TextField(
        value = "", onValueChange = {},
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = null,
                tint = Color.Gray,
            )
        },
        placeholder = { Text(text = "Search", color = Color.Gray) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(12.dp)
    )
}

@Composable
fun FavoriteTabs(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .border(1.dp, Color.LightGray, shape = RoundedCornerShape(50.dp))
    ) {
        TabRow(
            selectedTabIndex = selectedTab,
            indicator = {},
            divider = {}
        ) {
            TabItem(selectedTab, 0, "Food Items", onTabSelected)
            TabItem(selectedTab, 1, "Restaurants", onTabSelected)
        }
    }
}

@Composable
fun TabItem(selectedTab: Int, tabIndex: Int, text: String, onTabSelected: (Int) -> Unit) {
    Tab(
        selected = selectedTab == tabIndex,
        onClick = { onTabSelected(tabIndex) },
        modifier = Modifier.then(
            if (selectedTab == tabIndex) {
                Modifier
                    .background(
                        colorResource(R.color.purple_500),
                        RoundedCornerShape(50.dp)
                    )
                    .padding(8.dp)
            } else {
                Modifier.padding(4.dp)
            }
        )
    ) {
        Text(
            text = text,
            color = if (selectedTab == tabIndex) Color.White else Color.Gray,
            modifier = Modifier.padding(6.dp)
        )
    }
}

@Composable
fun FavoriteFoodItemCard(foodItem: FoodItem) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(350.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.4f)
            ) {
                Image(
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = foodItem.imageUrl),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )

                HeartIcon(
                    Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                )
            }
            ItemInfo(
                title = foodItem.title,
                description = foodItem.description,
                iconsWithText = listOf(
                    R.drawable.baseline_store_24 to foodItem.restaurantName,
                    R.drawable.ic_payment to foodItem.price,
                    R.drawable.ic_prep_time to foodItem.preparingTime,
                    R.drawable.ic_rating to foodItem.rating.toString()
                )
            )
        }
    }
}

@Composable
fun FavoriteRestaurantItemCard(restaurantItem: RestaurantItem) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(300.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.4f)
            ) {
                Image(
                    contentScale = ContentScale.FillBounds,
                    painter = painterResource(id = restaurantItem.image),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )

                HeartIcon(
                    Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                )
            }
            ItemInfo(
                title = restaurantItem.name,
                description = restaurantItem.description,
                iconsWithText = listOf(
                    R.drawable.ic_delivery to restaurantItem.deliveryFees,
                    R.drawable.ic_prep_time to restaurantItem.preparingTime,
                    R.drawable.ic_rating to restaurantItem.rating.toString()
                )
            )
        }
    }
}

@Composable
fun HeartIcon(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(40.dp)
            .background(colorResource(id = R.color.purple_500), shape = CircleShape)
            .padding(8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "Heart Icon",
            tint = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun ItemInfo(title: String, description: String, iconsWithText: List<Pair<Int, String>>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(4.dp)

    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                color = Color.Black
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = description,
                fontSize = 16.sp,
                color = Color.Gray
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                iconsWithText.forEach { (iconRes, text) ->
                    IconWithText(iconRes = iconRes, text = text)
                }
            }
        }
    }
}

@Composable
fun IconWithText(iconRes: Int, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(end = 4.dp)
    ) {
        Icon(
            tint = colorResource(id = R.color.teal_200),
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = text, fontSize = 14.sp, color = Color.Black)
    }
}

@Preview(showBackground = true)
@Composable
fun FavoritePreview() {
    FoodDeliveryTheme {
        FavoriteScreen(viewModel = FavoriteViewModel())
    }
}