package com.th.group.fooddelivery.ui.presentation.profile

import androidx.lifecycle.ViewModel
import com.th.group.fooddelivery.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel : ViewModel() {

    private val _profileData = MutableStateFlow(profile)
    val profileData: StateFlow<ProfileData> = _profileData
}

data class ProfileData(val id: Int, val userName: String, val email: String, val image: Int)

val profile = ProfileData(0, "TH Group", "tantawyhabib@gmail.com", R.drawable.th_group_img)