package com.th.group.fooddelivery.ui.presentation.search

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SearchViewModel : ViewModel() {

    private val _searchResultList = MutableStateFlow(searchItems)
    val searchResultList: StateFlow<List<SearchResultDto>> = _searchResultList

}

data class SearchResultDto(
    var id: Int,
    var name: String
)

val searchItems = listOf(
    SearchResultDto(
        id = 1,
        name = "Burger",
    ),
    SearchResultDto(
        id = 1,
        name = "Fast Food",
    ),
    SearchResultDto(
        id = 1,
        name = "Salad",
    ),
    SearchResultDto(
        id = 1,
        name = "Sushi",
    ),
    SearchResultDto(
        id = 1,
        name = "Desserts",
    ),
    SearchResultDto(
        id = 1,
        name = "Pizza",
    )
)