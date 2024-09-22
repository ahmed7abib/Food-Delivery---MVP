package com.th.group.fooddelivery.ui.presentation.search

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ContextualFlowRow
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.th.group.fooddelivery.ui.theme.Orange2
import com.th.group.fooddelivery.utils.Constants

@Composable
fun SearchScreen(searchViewModel: SearchViewModel) {

    val searchText = remember { mutableStateOf("") }
    val allSearchKeys by searchViewModel.searchResultList.collectAsState()

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {

        AutoCompleteSearchBar(
            keys = allSearchKeys,
            searchItem = searchText,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            searchText.value = it
        }

        if (allSearchKeys.isNotEmpty()) {
            MostPopularSearchItems(allSearchKeys, onItemClick = { searchText.value = it })
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MostPopularSearchItems(allSearchKeys: List<SearchResultDto>, onItemClick: (String) -> Unit) {

    Text(
        text = "Most Popular ${Constants.fireIcon}",
        color = Color.Black,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 12.dp, top = 10.dp, bottom = 20.dp)
    )

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        ContextualFlowRow(
            modifier = Modifier.animateContentSize(),
            itemCount = allSearchKeys.size,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Row {
                Button(
                    onClick = { onItemClick.invoke(allSearchKeys[it].name) },
                    colors = ButtonColors(Orange2, Orange2, Orange2, Orange2)
                ) {
                    Text(text = allSearchKeys[it].name, color = Color.White)
                }

                Spacer(modifier = Modifier.width(4.dp))
            }
        }
    }
}