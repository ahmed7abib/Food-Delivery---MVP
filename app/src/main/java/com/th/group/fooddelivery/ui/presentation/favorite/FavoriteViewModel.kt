package com.th.group.fooddelivery.ui.presentation.favorite

import androidx.lifecycle.ViewModel
import com.th.group.fooddelivery.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FavoriteViewModel : ViewModel() {

    private val _favoriteFoodItems = MutableStateFlow(foodItems)
    val favoriteFoodItems: StateFlow<List<FoodItem>> = _favoriteFoodItems

    private val _favoriteRestaurantItems = MutableStateFlow(restaurantItems)
    val favoriteRestaurantItems: StateFlow<List<RestaurantItem>> = _favoriteRestaurantItems
}

data class FoodItem(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: Int,
    val restaurantName: String,
    val price: String,
    val preparingTime: String,
    val rating: Double,
)

data class RestaurantItem(
    val id: Int,
    val name: String,
    val description: String,
    val image: Int,
    val rating: Double,
    val deliveryFees: String,
    val preparingTime: String,
)

val foodItems = listOf(
    FoodItem(
        id = 1,
        title = "Margherita Pizza",
        description = "Classic pizza with tomato sauce, mozzarella, and basil.",
        imageUrl = R.drawable.img_margherita_pizza,
        restaurantName = "Italiano",
        price = "$8.99",
        preparingTime = "15 mins",
        rating = 4.5
    ),
    FoodItem(
        id = 2,
        title = "Cheeseburger",
        description = "Juicy beef patty with cheese, lettuce, and tomato.",
        imageUrl = R.drawable.img_cheese_burger ,
        restaurantName = "Burger King",
        price = "$5.99",
        preparingTime = "10 mins",
        rating = 4.2
    ),
    FoodItem(
        id = 3,
        title = "Sushi Platter",
        description = "Assorted sushi rolls with fresh fish and vegetables.",
        imageUrl = R.drawable.img_sushi,
        restaurantName = "Sushi House",
        price = "$12.99",
        preparingTime = "20 mins",
        rating = 4.8
    ),
    FoodItem(
        id = 4,
        title = "Chicken Tacos",
        description = "Soft tacos filled with grilled chicken, avocado, and salsa.",
        imageUrl = R.drawable.img_chicken_tacos,
        restaurantName = "Mexicana",
        price = "$7.99",
        preparingTime = "12 mins",
        rating = 4.3
    ),
    FoodItem(
        id = 5,
        title = "Caesar Salad",
        description = "Fresh romaine lettuce with Caesar dressing and croutons.",
        imageUrl = R.drawable.img_caesar_salad,
        restaurantName = "Green Eatery",
        price = "$6.99",
        preparingTime = "8 mins",
        rating = 4.6
    )
)

val restaurantItems = listOf(
    RestaurantItem(
        id = 1,
        name = "Trattoria",
        description = "Authentic Italian cuisine with a modern twist.",
        image = R.drawable.img_trattoria_restaurant,
        rating = 4.7,
        deliveryFees = "$3.50",
        preparingTime = "30 mins"
    ),
    RestaurantItem(
        id = 2,
        name = "Burger King",
        description = "Home of the Whopper and other delicious burgers.",
        image = R.drawable.img_burger_king_restaurant,
        rating = 4.2,
        deliveryFees = "$2.00",
        preparingTime = "20 mins"
    ),
    RestaurantItem(
        id = 3,
        name = "Sushi House",
        description = "Fresh and delicious sushi made to order.",
        image = R.drawable.img_sushi_house_restaurant,
        rating = 4.8,
        deliveryFees = "$4.00",
        preparingTime = "25 mins"
    ),
    RestaurantItem(
        id = 4,
        name = "Tropez",
        description = "Authentic Mexican dishes with bold flavors.",
        image = R.drawable.img_tropez_restaurant,
        rating = 4.5,
        deliveryFees = "$2.50",
        preparingTime = "18 mins"
    ),
    RestaurantItem(
        id = 5,
        name = "Green Eatery",
        description = "Healthy and fresh vegetarian and vegan options.",
        image = R.drawable.img_green_eatery_restaurant,
        rating = 4.6,
        deliveryFees = "$3.00",
        preparingTime = "15 mins"
    )
)