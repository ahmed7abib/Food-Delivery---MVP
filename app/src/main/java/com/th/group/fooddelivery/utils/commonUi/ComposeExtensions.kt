package com.th.group.fooddelivery.utils.commonUi

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun <T> HorizontalListView(
    modifier: Modifier,
    items: List<T>,
    itemContent: @Composable (T) -> Unit
) {
    LazyRow(modifier = modifier) {
        items(items) { item ->
            itemContent(item)
        }
    }
}

@Composable
fun <T> VerticalListView(modifier: Modifier, items: List<T>, itemContent: @Composable (T) -> Unit) {
    LazyColumn(modifier = modifier) { items(items) { item -> itemContent(item) } }
}

@Composable
fun SetSystemBarsColor(
    statusBarColor: Color = Color.White,
    useDarkIcons: Boolean = true
) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = statusBarColor,
            darkIcons = useDarkIcons
        )
    }
}