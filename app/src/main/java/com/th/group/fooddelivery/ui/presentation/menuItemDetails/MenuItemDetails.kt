package com.th.group.fooddelivery.ui.presentation.menuItemDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.th.group.fooddelivery.R
import com.th.group.fooddelivery.ui.presentation.restaurant.Dish
import com.th.group.fooddelivery.ui.presentation.restaurant.Extra
import com.th.group.fooddelivery.ui.presentation.restaurant.divider
import com.th.group.fooddelivery.ui.presentation.restaurant.header
import com.th.group.fooddelivery.ui.presentation.restaurant.priceTag
import com.th.group.fooddelivery.ui.theme.Orange0
import com.th.group.fooddelivery.ui.theme.Orange1

@Composable
fun MenuItemDetailsScreen(navHostController: NavHostController) {
    val data =
        navHostController.previousBackStackEntry?.savedStateHandle?.get<Dish>("data") ?: Dish(
            -1,
            "",
            "",
            0.0,
            null,
            0,
            null,
            null
        )

    val scrollState = rememberLazyListState()
    val showHeaderImage by remember {
        derivedStateOf { scrollState.firstVisibleItemIndex == 0 }
    }

    // Manage the selected option state for required extras at a higher level
    var requiredOption by remember { mutableStateOf(data.requiredSelection?.firstOrNull()) }
    var optionalSelection by remember {
        mutableStateOf(
            data.optionalSelection?.map { it to false }?.toMutableStateList()
                ?: mutableStateListOf()
        )
    }
    var orderCount by remember { mutableIntStateOf(1) }

    if (data.id != -1) {
        Scaffold(
            bottomBar = {
                val totalPrice =
                    calculateTotalPrice(data.price, requiredOption, optionalSelection, orderCount)
                ActionButton("Add to order", String.format("%.2f", totalPrice))
            },
            content = { paddingValues ->
                LazyColumn(
                    state = scrollState,
                    contentPadding = paddingValues,
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (showHeaderImage) {
                        item {
                            header(imageRes = data.image)
                        }
                    }
                    item {

                        DishDetails(
                            dish = data,
                            orderCount = orderCount,
                            onOrderCountChange = { newCount ->
                                orderCount = newCount
                            })
                    }
                    if (data.requiredSelection != null) {
                        item {
                            Text(
                                text = "Required",
                                style = MaterialTheme.typography.headlineLarge,
                                modifier = Modifier.padding(start = 16.dp, bottom = 8.0.dp)
                            )
                        }
                        itemsIndexed(data.requiredSelection) { index, item ->

                            RequiredFoodExtraItem(
                                item = item,
                                isSelected = item == requiredOption,
                                onClick = { requiredOption = item },
                                index = index,
                                len = data.requiredSelection.size
                            )
                        }
                    }
                    if (data.optionalSelection != null) {
                        item {
                            Text(
                                text = "Optional",
                                style = MaterialTheme.typography.headlineLarge,
                                modifier = Modifier.padding(
                                    start = 16.dp,
                                    top = 8.0.dp,
                                    bottom = 8.0.dp
                                )
                            )
                        }
                        itemsIndexed(data.optionalSelection) { index, item ->

                            val isChecked = optionalSelection[index].second
                            OptionalFoodExtraItem(
                                item = item,
                                isChecked = isChecked,
                                onCheckedChange = { optionalSelection[index] = item to !isChecked },
                                index = index,
                                len = data.optionalSelection.size
                            )
                        }
                    }

                }

            }
        )
    }
}

@Composable
fun ActionButton(label: String, value: String) {
    Box(
        Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .background(Orange0, shape = RoundedCornerShape(50))
                .fillMaxWidth()
                .padding(16.dp)

        ) {
            Text(
                text = label,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )
            Text(
                text = "$${value}",
                color = Color.White,
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    }
}


@Composable
fun RequiredFoodExtraItem(
    item: Extra,
    isSelected: Boolean,
    index: Int,
    len: Int,
    onClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .selectable(selected = isSelected, onClick = onClick)
            .padding(start = 16.dp)
    ) {
        RadioButton(
            colors = RadioButtonDefaults.colors(
                selectedColor = colorResource(id = R.color.purple_500),
                unselectedColor = Color.Gray
            ),
            selected = isSelected,
            onClick = onClick
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.0.dp)
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 8.dp)
            )
            Text(
                text = "$${item.price}",
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
    if (index < len - 1) {
        divider(thickness = 1, color = Color.LightGray)
    }
}

@Composable
fun OptionalFoodExtraItem(
    item: Extra,
    isChecked: Boolean,
    index: Int,
    len: Int,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp)
            .clickable { onCheckedChange(!isChecked) }

    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = colorResource(id = R.color.purple_500),
                uncheckedColor = Color.Gray
            )
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.0.dp)
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 8.dp)
            )
            Text(
                text = "$${item.price}",
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
    if (index < len - 1) {
        divider(thickness = 1, color = Color.LightGray)
    }
}

@Composable
fun DishDetails(dish: Dish, orderCount: Int, onOrderCountChange: (Int) -> Unit) {

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
                text = dish.title,
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
            text = dish.ingredients, fontSize = 16.sp, modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            Modifier
                .fillMaxWidth()
                .height(36.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            priceTag(price = dish.price, discount = dish.discount)
            Row(
                horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                    .background(Orange1, shape = RoundedCornerShape(50))
            ) {

                IconButton(onClick = { if (orderCount > 1) onOrderCountChange(orderCount - 1) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_indeterminate_check_box_24),
                        contentDescription = "Remove Item",
                        tint = Orange0
                    )
                }
                Text(
                    text = orderCount.toString(),
                    color = colorResource(id = R.color.purple_500),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                IconButton(onClick = { onOrderCountChange(orderCount + 1) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_add_box_24),
                        contentDescription = "Add Item",
                        tint = Orange0
                    )

                }
            }
        }

    }
}

fun calculateTotalPrice(
    basePrice: Double,
    requiredOption: Extra?,
    optionalSelection: List<Pair<Extra, Boolean>>?,
    orderCount: Int
): Double {
    val requiredExtraPrice = requiredOption?.price ?: basePrice
    val optionalExtraPrice = optionalSelection!!.filter { it.second }.sumOf { it.first.price }
    return orderCount * (requiredExtraPrice + optionalExtraPrice)
}

