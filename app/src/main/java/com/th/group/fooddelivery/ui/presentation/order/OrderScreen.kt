package com.th.group.fooddelivery.ui.presentation.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.th.group.fooddelivery.R
import com.th.group.fooddelivery.ui.theme.Bink
import com.th.group.fooddelivery.ui.theme.Orange0
import com.th.group.fooddelivery.ui.theme.Orange1
import com.th.group.fooddelivery.utils.commonUi.VerticalListView

@Composable
fun OrderScreen(viewModel: OrderViewModel) {

    val orderItems by viewModel.orderItems.collectAsState()

    Column {

        Box(
            modifier = Modifier
                .padding(top = 30.dp, start = 30.dp, end = 30.dp, bottom = 8.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = null,
                    modifier = Modifier.size(25.dp),
                    tint = Color.White
                )
            }

            Text(
                text = "Your Order",
                fontSize = 16.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Box(
            Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = "Order Items",
                    fontSize = 18.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )

                VerticalListView(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxSize(), items = orderItems
                ) {
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clip(RoundedCornerShape(10.dp))
                    ) {
                        val (imageBox, textColumn, emptyBox) = createRefs()

                        Box(modifier = Modifier
                            .constrainAs(imageBox) {
                                start.linkTo(parent.start)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                width = Dimension.percent(0.3f)
                                height = Dimension.fillToConstraints
                            }
                            .background(Bink),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = it.imageUrl),
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier.padding(8.dp)
                            )
                        }

                        Column(
                            modifier = Modifier
                                .constrainAs(textColumn) {
                                    start.linkTo(imageBox.end)
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                    width = Dimension.percent(0.5f)
                                }
                                .padding(horizontal = 16.dp),
                        ) {

                            Text(
                                text = it.name,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(0.dp)
                            )

                            Spacer(modifier = Modifier.height(2.dp))

                            it.additions.forEach { item ->
                                Text(
                                    text = "+ $item",
                                    color = Color.Black,
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(0.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "$${it.price}",
                                color = Orange0,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                            )
                        }

                        Box(
                            modifier = Modifier
                                .constrainAs(emptyBox) {
                                    start.linkTo(textColumn.end)
                                    top.linkTo(parent.top)
                                    end.linkTo(parent.end)
                                    bottom.linkTo(parent.bottom)
                                    width = Dimension.percent(0.2f)
                                    height = Dimension.fillToConstraints
                                },
                            contentAlignment = Alignment.TopStart
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(horizontal = 2.dp)
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Orange1)
                                    .padding(6.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Box(
                                    modifier = Modifier
                                        .size(16.dp)
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(Orange0)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_minimize_24),
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier
                                            .size(14.dp)
                                            .align(Alignment.Center)
                                    )
                                }

                                Text(
                                    text = it.itemNumbers.toString(),
                                    color = colorResource(id = R.color.purple_500),
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )

                                Box(
                                    modifier = Modifier
                                        .size(16.dp)
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(Orange0)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier
                                            .size(14.dp)
                                            .align(Alignment.Center)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Orange0)
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Go to checkout",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White,
                    modifier = Modifier.padding(start = 16.dp)
                )

                Text(
                    text = "$${getPrice()}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
        }
    }
}

fun getPrice(): Double {
    return orderItemsList.sumOf { it.price.toDouble() }
}