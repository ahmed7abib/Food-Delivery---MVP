package com.th.group.fooddelivery.ui.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.th.group.fooddelivery.R
import com.th.group.fooddelivery.ui.presentation.order.OrderScreen
import com.th.group.fooddelivery.ui.presentation.order.OrderViewModel
import com.th.group.fooddelivery.ui.presentation.favorite.FavoriteScreen
import com.th.group.fooddelivery.ui.presentation.favorite.FavoriteViewModel
import com.th.group.fooddelivery.ui.presentation.home.HomeScreen
import com.th.group.fooddelivery.ui.presentation.home.HomeViewModel
import com.th.group.fooddelivery.ui.presentation.menuItemDetails.MenuItemDetailsScreen
import com.th.group.fooddelivery.ui.presentation.profile.ProfileScreen
import com.th.group.fooddelivery.ui.presentation.profile.ProfileViewModel
import com.th.group.fooddelivery.ui.presentation.restaurant.RestaurantDetailsScreen
import com.th.group.fooddelivery.ui.presentation.restaurant.RestaurantsViewModel
import com.th.group.fooddelivery.ui.presentation.search.SearchScreen
import com.th.group.fooddelivery.ui.presentation.search.SearchViewModel
import com.th.group.fooddelivery.ui.theme.Orange0


@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val items = listOf(
        BottomNavScreen.Home,
        BottomNavScreen.Order,
        BottomNavScreen.Search,
        BottomNavScreen.Favorite,
        BottomNavScreen.Profile
    )

    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            if (currentRoute in items.map { it.route }) {
                BottomAppBar(
                    containerColor = Color.Transparent
                ) {
                    if (currentRoute != null) {
                        BottomNavItems(navController, items, currentRoute)
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = BottomNavScreen.Home.route,
            Modifier.padding(innerPadding)
        ) {
            composable(BottomNavScreen.Home.route) {
                HomeScreen(
                    navController,
                    HomeViewModel(),
                    RestaurantsViewModel()
                )
            }

            composable(BottomNavScreen.Order.route) { OrderScreen(OrderViewModel()) }

            composable(BottomNavScreen.Search.route) { SearchScreen(SearchViewModel()) }

            composable(BottomNavScreen.Favorite.route) { FavoriteScreen(FavoriteViewModel()) }

            composable(BottomNavScreen.Profile.route) { ProfileScreen(ProfileViewModel()) }

            composable(DetailsScreens.RestaurantDetailsScreen.route) {
                RestaurantDetailsScreen(navHostController = navController)
            }

            composable(DetailsScreens.MenuItemDetailsScreen.route) {
                MenuItemDetailsScreen(navController)
            }
        }
    }
}

@Composable
fun BottomNavItems(
    navController: NavHostController,
    items: List<BottomNavScreen>,
    currentRoute: String
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {

        Image(
            painter = painterResource(id = R.drawable.bottom_nav_img),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
        )

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { screen ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    IconButton(
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    ) {
                        if (screen == BottomNavScreen.Order) {
                            Icon(
                                painter = painterResource(id = R.drawable.market_img),
                                contentDescription = screen.route,
                                tint = if (currentRoute == screen.route) Orange0 else Color.Black
                            )
                        } else {
                            val icon = when (screen) {
                                BottomNavScreen.Home -> Icons.Default.Home
                                BottomNavScreen.Search -> Icons.Default.Search
                                BottomNavScreen.Favorite -> Icons.Default.Favorite
                                BottomNavScreen.Profile -> Icons.Default.Person
                                BottomNavScreen.Order -> TODO()
                            }

                            Icon(
                                icon,
                                contentDescription = screen.route,
                                tint = if (currentRoute == screen.route) Orange0 else Color.Black
                            )
                        }
                    }

                    Text(
                        text = screen.route,
                        fontSize = 14.sp,
                        color = if (currentRoute == screen.route) Orange0 else Color.Black
                    )
                }
            }
        }
    }
}