package com.th.group.fooddelivery.ui.presentation.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.th.group.fooddelivery.R

@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {

    val profileData by viewModel.profileData.collectAsState()

    Column {

        Header(
            profileData,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
        )

        Body(
            listOf(
                R.drawable.ic_person to "My Account",
                R.drawable.ic_orders to "My Orders",
                R.drawable.ic_delivery_address to "Delivery Address",
                R.drawable.ic_payment to "Payment Methods",
                R.drawable.ic_contact_us to "Contact Us",
                R.drawable.ic_settings to "Settings",
                R.drawable.ic_help to "Help & FAQ"
            ),
            modifier = Modifier
                .padding(16.dp)
                .weight(1f)
        )

        Footer(
            modifier = Modifier.padding(start = 32.dp, top = 4.dp, bottom = 8.dp)
        )
    }
}

@Composable
fun Header(profileData: ProfileData, modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = profileData.image),
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Text(
            text = profileData.userName,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 10.dp)
        )

        Text(
            text = profileData.email,
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun Body(iconsWithText: List<Pair<Int, String>>, modifier: Modifier) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        iconsWithText.forEach { (iconRes, text) ->
            IconWithText(iconRes = iconRes, text = text)
        }
    }
}

@Composable
fun IconWithText(iconRes: Int, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(16.dp)
    ) {
        Icon(
            tint = colorResource(id = R.color.teal_200),
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, fontSize = 16.sp, color = Color.Black, fontWeight = FontWeight.W400)
    }
}

@Composable
fun Footer(modifier: Modifier) {
    Button(
        onClick = { /*TODO*/ },
        modifier = modifier,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White),
        border = BorderStroke(1.dp, color = colorResource(id = R.color.purple_500))
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_logout),
            contentDescription = "Logout",
            tint = colorResource(
                id = R.color.purple_500
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Log out", color = colorResource(id = R.color.purple_500), fontSize = 16.sp)
    }
}
