package com.th.group.fooddelivery.ui.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.th.group.fooddelivery.R
import com.th.group.fooddelivery.ui.theme.Grey0
import com.th.group.fooddelivery.utils.commonUi.VerticalListView


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressDetailsBottomSheet(
    addressList: List<AddressDto>,
    onDismiss: (AddressDto?) -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss(null) },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        containerColor = Color.White
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Saved Addresses",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            VerticalListView(
                modifier = Modifier.fillMaxWidth(),
                items = addressList
            ) {
                Column(modifier = Modifier.clickable { onDismiss(it) }) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 10.dp)
                    ) {
                        if (it.addressType == AddressType.HOME) {
                            Image(
                                painter = painterResource(id = R.drawable.home_icon),
                                contentDescription = null,
                                modifier = Modifier.size(30.dp)
                            )
                        } else {
                            Image(
                                painter = painterResource(id = R.drawable.office_icon),
                                contentDescription = null,
                                modifier = Modifier.size(30.dp)
                            )
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp)
                        ) {
                            Text(
                                text = it.addressType.value,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )

                            Spacer(modifier = Modifier.height(2.dp))

                            Text(
                                text = "${it.blockNumber}. ${it.streetName}, ${it.city}",
                                color = Color.Gray,
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp
                            )
                        }
                    }

                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp, horizontal = 15.dp),
                        thickness = 1.dp,
                        color = Grey0
                    )
                }
            }

            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}