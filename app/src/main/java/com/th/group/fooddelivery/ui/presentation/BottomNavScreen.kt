package com.th.group.fooddelivery.ui.presentation

import com.th.group.fooddelivery.ui.presentation.Routes.FAVORITE_SCREEN
import com.th.group.fooddelivery.ui.presentation.Routes.HOME_SCREEN
import com.th.group.fooddelivery.ui.presentation.Routes.MENU_ITEM_DETAILS_SCREEN
import com.th.group.fooddelivery.ui.presentation.Routes.ORDER_SCREEN
import com.th.group.fooddelivery.ui.presentation.Routes.PROFILE_SCREEN
import com.th.group.fooddelivery.ui.presentation.Routes.RESTAURANT_DETAILS_SCREEN
import com.th.group.fooddelivery.ui.presentation.Routes.SEARCH_SCREEN

object Routes {
    const val HOME_SCREEN = "Home"
    const val SEARCH_SCREEN = "Search"
    const val FAVORITE_SCREEN = "Favorite"
    const val PROFILE_SCREEN = "Profile"
    const val ORDER_SCREEN = "Order"
    const val RESTAURANT_DETAILS_SCREEN = "RestaurantDetails"
    const val MENU_ITEM_DETAILS_SCREEN = "MenuItemDetails"
}

sealed class BottomNavScreen(val route: String) {
    data object Home : BottomNavScreen(HOME_SCREEN)
    data object Search : BottomNavScreen(SEARCH_SCREEN)
    data object Favorite : BottomNavScreen(FAVORITE_SCREEN)
    data object Profile : BottomNavScreen(PROFILE_SCREEN)
    data object Order : BottomNavScreen(ORDER_SCREEN)
}

sealed class DetailsScreens(val route: String) {
    data object RestaurantDetailsScreen : DetailsScreens(RESTAURANT_DETAILS_SCREEN)
    data object MenuItemDetailsScreen : DetailsScreens(MENU_ITEM_DETAILS_SCREEN)

}