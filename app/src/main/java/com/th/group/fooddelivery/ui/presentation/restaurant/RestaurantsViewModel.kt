package com.th.group.fooddelivery.ui.presentation.restaurant

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import com.th.group.fooddelivery.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.parcelize.Parcelize

class RestaurantsViewModel : ViewModel() {
    private val _restaurants = MutableStateFlow(restaurantList)
    val restaurants: MutableStateFlow<List<Restaurant>> = _restaurants
}

@Parcelize
data class Restaurant(
    val id: Int,
    val name: String,
    val description: String,
    val deliveryTime: Int,
    val deliveryFees: Double,
    val address: String,
    val rating: Double,
    val operatingTime: String,
    val image: Int,
    val menu: List<Pair<String, List<Dish>>>?,
): Parcelable

@Parcelize
data class Dish(
    val id: Int,
    val title: String,
    val ingredients: String,
    val price: Double,
    val discount: Double?,
    val image: Int,
    val requiredSelection: List<Extra>?,
    val optionalSelection: List<Extra>?,
) : Parcelable

@Parcelize
data class Extra(val title: String, val price: Double) : Parcelable

val menu = listOf(
    Pair(
        "Popular Items",
        listOf(
            Dish(
                id = 0,
                title = "Spaghetti Carbonara",
                ingredients = "Spaghetti, eggs, pancetta, Parmesan cheese, black pepper",
                price = 12.99,
                discount = 13.99,
                image = R.drawable.img_caesar_salad,
                requiredSelection = listOf(
                    Extra("Small", 12.99),
                    Extra("Medium", 13.99),
                    Extra("Large", 14.99)
                ),
                optionalSelection = listOf(
                    Extra("Parmesan Cheese", 3.99),
                    Extra("Alfredo sauce", 5.0)
                ),
            ),
            Dish(
                id = 1,
                title = "Margherita Pizza",
                ingredients = "Tomato sauce, mozzarella, fresh basil, olive oil",
                price = 10.99,
                discount = 12.50,
                image = R.drawable.img_margherita_pizza,
                requiredSelection = listOf(
                    Extra("Small", 10.99),
                    Extra("Medium", 13.99),
                    Extra("Large", 14.99)
                ),
                optionalSelection = listOf(
                    Extra("Mozzarella Cheese", 3.0),
                    Extra("White sauce", 5.0),
                    Extra("Ranch sauce", 4.0)
                ),

                ),
            Dish(
                id = 2,
                title = "Lasagna Bolognese",
                ingredients = "Lasagna noodles, Bolognese sauce, béchamel sauce, Parmesan cheese",
                price = 12.99,
                discount = null,
                image = R.drawable.img_cheese_burger,
                requiredSelection = listOf(
                    Extra("Small", 12.99),
                    Extra("Medium", 13.99),
                    Extra("Large", 14.99)
                ),
                optionalSelection = listOf(
                    Extra("Bolognese sauce", 4.0),
                    Extra("Parmesan Cheese", 6.0),
                    Extra("Béchamel sauce", 5.0)
                ),
            ),
        )
    ),
    Pair(
        "Main Dishes",
        listOf(
            Dish(
                id = 3,
                title = "Fettuccine Alfredo",
                ingredients = "Fettuccine, Alfredo sauce, Parmesan cheese, butter, garlic",
                price = 11.99,
                discount = 12.99,
                image = R.drawable.img_chicken_tacos,
                requiredSelection = listOf(
                    Extra("Small", 11.99),
                    Extra("Medium", 12.99),
                    Extra("Large", 13.99)
                ),
                optionalSelection = listOf(
                    Extra("Bolognese sauce", 4.0),
                    Extra("Alfredo sauce", 6.0)
                ),
            ),
            Dish(
                id = 4,
                title = "Chicken Parmigiana",
                ingredients = "Breaded chicken, marinara sauce, mozzarella, Parmesan, spaghetti",
                price = 11.99,
                discount = null,
                image = R.drawable.img_caesar_salad,
                requiredSelection = null,
                optionalSelection = listOf(
                    Extra("Marinara sauce", 4.0),
                    Extra("Mozzarella Cheese", 6.0),
                    Extra("Parmesan Cheese", 6.0)
                ),
            ),
            Dish(
                id = 5,
                title = "Penne Arrabbiata",
                ingredients = "Penne, spicy tomato sauce, garlic, red pepper flakes, parsley",
                price = 10.99,
                discount = null,
                image = R.drawable.img_margherita_pizza,
                requiredSelection = null,
                optionalSelection = null,
            ),
        )
    ),
    Pair(
        "Appetizers",
        listOf(
            Dish(
                id = 6,
                title = "Bruschetta",
                ingredients = "Grilled bread, tomatoes, garlic, basil, olive oil, balsamic vinegar",
                price = 5.99,
                discount = null,
                image = R.drawable.img_sushi,
                null, null
            ),
            Dish(
                id = 7,
                title = "Caprese Salad",
                ingredients = "Fresh mozzarella, tomatoes, basil, olive oil, balsamic glaze",
                price = 7.0,
                discount = null,
                image = R.drawable.img_chicken_tacos,
                requiredSelection = listOf(
                    Extra("Small", 7.0),
                    Extra("Medium", 7.50),
                    Extra("Large", 8.99)
                ),null
            ),
            Dish(
                id = 8,
                title = "Garlic Bread",
                ingredients = "Bread, garlic, butter, parsley",
                price = 4.99,
                discount = null,
                image = R.drawable.img_caesar_salad,null,null
            ),
        )
    ),
    Pair(
        "Desserts",
        listOf(
            Dish(
                id = 9,
                title = "Tiramisu",
                ingredients = "Mascarpone cheese, espresso, ladyfingers, cocoa powder",
                price = 6.0,
                discount = null,
                image = R.drawable.img_cheese_burger,requiredSelection = listOf(
                    Extra("Small", 6.0),
                    Extra("Medium", 6.50),
                    Extra("Large", 7.99)
                ),null
            ),
            Dish(
                id = 10,
                title = "Cannoli",
                ingredients = "Ricotta cheese, chocolate chips, cannoli shells, powdered sugar",
                price = 5.0,
                discount = null,
                image = R.drawable.img_sushi,
                requiredSelection = listOf(
                    Extra("Small", 5.0),
                    Extra("Medium", 5.50),
                    Extra("Large", 6.99)
                ),null
            ),
            Dish(
                id = 11,
                title = "Panna Cotta",
                ingredients = "Cream, sugar, vanilla, berry sauce",
                price = 7.0,
                discount = null,
                image = R.drawable.img_margherita_pizza,
                requiredSelection = listOf(
                    Extra("Small", 7.0),
                    Extra("Medium", 7.50),
                    Extra("Large", 8.99)
                ),null
            ),
        )
    ),
)
//val restaurantData = Restaurant(
//    0,
//    "Trattoria",
//    "An authentic Italian touch and delious!",
//    40,
//    "Home, Jl. Soekarno Hatta 15A",
//    4.6,
//    "10:00 - 22:00",
//    R.drawable.img_trattoria_restaurant,
//    menu
//)

val restaurantList = listOf(
    Restaurant(
        id = 1,
        name = "Trattoria",
        description = "Authentic Italian cuisine with a modern twist.",
        image = R.drawable.img_trattoria_restaurant,
        rating = 4.6,
        address = "Home, Jl. Soekarno Hatta 15A",
        deliveryFees = 3.50,
        deliveryTime = 30,
        operatingTime = "8 AM - 10 PM",
        menu = menu
    ),
    Restaurant(
        id = 2,
        name = "Burger King",
        description = "Home of the Whopper and other delicious burgers.",
        image = R.drawable.img_burger_king_restaurant,
        rating = 4.2,
        deliveryFees = 2.5,
        deliveryTime = 25,
        operatingTime = "10 AM - 10 PM",
        address = "Home, Jl. Soekarno Hatta 15A",
        menu = menu
    ),
    Restaurant(
        id = 3,
        name = "Sushi House",
        description = "Fresh and delicious sushi made to order.",
        image = R.drawable.img_sushi_house_restaurant,
        rating = 4.8,deliveryFees = 5.0,
        deliveryTime = 35,
        operatingTime = "8 AM - 12 AM",
        address = "Home, Jl. Soekarno Hatta 15A",
        menu = menu
    ),
    Restaurant(
        id = 4,
        name = "Tropez",
        description = "Authentic Mexican dishes with bold flavors.",
        image = R.drawable.img_tropez_restaurant,
        rating = 4.5,
        deliveryFees = 3.5,
        deliveryTime = 25,
        operatingTime = "8 AM - 12 AM",
        address = "Home, Jl. Soekarno Hatta 15A",
        menu = menu
    ),
    Restaurant(
        id = 5,
        name = "Green Eatery",
        description = "Healthy and fresh vegetarian and vegan options.",
        image = R.drawable.img_green_eatery_restaurant,
        rating = 4.6,
        deliveryFees = 4.0,
        deliveryTime = 25,
        operatingTime = "8 AM - 12 AM",
        address = "Home, Jl. Soekarno Hatta 15A",
        menu = menu
    )
)

