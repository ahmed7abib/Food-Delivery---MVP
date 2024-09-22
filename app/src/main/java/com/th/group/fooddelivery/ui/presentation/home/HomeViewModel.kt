package com.th.group.fooddelivery.ui.presentation.home

import androidx.lifecycle.ViewModel
import com.th.group.fooddelivery.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {

    private val _promotionList = MutableStateFlow(promotionItems)
    val promotionList: StateFlow<List<PromotionDto>> = _promotionList

    private val _categoryList = MutableStateFlow(categoryItems)
    val categoryList: StateFlow<List<CategoryDto>> = _categoryList

    private val _addressList = MutableStateFlow(addressItems)
    val addressList: StateFlow<List<AddressDto>> = _addressList
}

data class PromotionDto(
    var promotionText: String,
    var promotionImage: Int, // Todo: until get value of it from server will replace int to string.
    var actionText: String,
    var actionType: ActionType
)

enum class ActionType(val type: Int) {
    ORDER_NOW(0),
    COPY_PROMOTION(1)
}

val promotionItems = listOf(
    PromotionDto(
        promotionText = "Get your 30% daily discount now!",
        promotionImage = R.drawable.food_test_img,
        actionText = "Order Now",
        actionType = ActionType.ORDER_NOW
    ),
    PromotionDto(
        promotionText = "Copy this promotion (TH-G2024) and take 50% off for a first order.",
        promotionImage = R.drawable.promotion_img,
        actionText = "Copy Promotion",
        actionType = ActionType.COPY_PROMOTION
    ),
    PromotionDto(
        promotionText = "Take this meal for half price.",
        promotionImage = R.drawable.meal_icon,
        actionText = "Order Now",
        actionType = ActionType.ORDER_NOW
    ),
)

data class CategoryDto(
    var image: Int,
    var name: String,
    var placesNumbers: Int
)

val categoryItems = listOf(
    CategoryDto(
        image = R.drawable.brunch_img,
        name = "Brunch",
        placesNumbers = 94
    ),
    CategoryDto(
        image = R.drawable.sea_food_img,
        name = "Sea Food",
        placesNumbers = 50
    ),
    CategoryDto(
        image = R.drawable.dessert_img,
        name = "Dessert",
        placesNumbers = 34
    )
)

data class AddressDto(
    val addressType: AddressType,
    val blockNumber: String,
    val floatNumber: Int,
    val houseNumber: Int,
    val streetName: String,
    val city: String
)

enum class AddressType(val value: String) {
    HOME("Home"), OFFICE("Office")
}

val addressItems = listOf(
    AddressDto(
        addressType = AddressType.HOME,
        blockNumber = "8 A",
        houseNumber = 1,
        floatNumber = 4,
        streetName = "Waraqa ebn nofal",
        city = "6 October"
    ),
    AddressDto(
        addressType = AddressType.OFFICE,
        blockNumber = "10",
        houseNumber = 1,
        floatNumber = 1,
        streetName = "October Hills 1, El-Sewedy Electrometer",
        city = "6 October"
    )
)