package com.th.group.fooddelivery.ui.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.th.group.fooddelivery.ui.theme.FoodDeliveryTheme
import com.th.group.fooddelivery.utils.commonUi.SetSystemBarsColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SetSystemBarsColor()
            FoodDeliveryTheme(darkTheme = false) {
                MainScreen()
//                DetailsScreen()
            }
        }
    }
}