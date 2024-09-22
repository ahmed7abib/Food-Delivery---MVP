package com.th.group.fooddelivery.ui.presentation.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.th.group.fooddelivery.ui.theme.Grey1

@Composable
fun AutoCompleteSearchBar(
    modifier: Modifier = Modifier,
    keys: List<SearchResultDto>,
    searchItem: MutableState<String>,
    onSelect: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    Column(
        modifier = modifier.clickable(
            onClick = { expanded = false }
        )
    ) {
        Column {
            Row {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            textFieldSize = coordinates.size.toSize()
                        },
                    shape = RoundedCornerShape(14.dp),
                    placeholder = { Text(text = "Search", color = Color.Gray) },
                    value = searchItem.value,
                    onValueChange = {
                        searchItem.value = it
                        expanded = true
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = null,
                            tint = Color.Gray,
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedContainerColor = Grey1,
                        unfocusedContainerColor = Grey1
                    ),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    singleLine = true,
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            AnimatedVisibility(visible = expanded) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Grey1)
                ) {
                    LazyColumn {
                        if (searchItem.value.isNotEmpty()) {
                            items(
                                keys.filter {
                                    it.name.lowercase()
                                        .contains(searchItem.value.lowercase()) || it.name.lowercase()
                                        .contains("others")
                                }.sortedBy { it.id }
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            searchItem.value = it.name
                                            onSelect(it.name)
                                            expanded = false
                                        }
                                        .padding(10.dp)
                                ) {
                                    Text(text = it.name, fontSize = 14.sp)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}