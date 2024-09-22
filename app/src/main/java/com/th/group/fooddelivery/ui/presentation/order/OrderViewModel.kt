package com.th.group.fooddelivery.ui.presentation.order

import androidx.lifecycle.ViewModel
import com.th.group.fooddelivery.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class OrderViewModel : ViewModel() {

    private val _orderItems = MutableStateFlow(orderItemsList)
    val orderItems: StateFlow<List<OrderItem>> = _orderItems
}

data class OrderItem(
    val id: Int,
    val imageUrl: Int,
    val name: String,
    val price: String,
    val itemNumbers: Int,
    val additions: List<String>
)

val orderItemsList = listOf(
    OrderItem(
        id = 1,
        imageUrl = R.drawable.brunch_img,
        name = "Margherita Pizza",
        price = "12.50",
        itemNumbers = 2,
        additions = listOf("Packaging fees", "Parmesan Cheese")
    ),
    OrderItem(
        id = 2,
        imageUrl = R.drawable.sea_food_img,
        name = "Fish",
        price = "3.5",
        itemNumbers = 2,
        additions = listOf("Meat", "Parmesan Cheese")
    ),
)