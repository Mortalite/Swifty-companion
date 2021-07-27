package com.example.swifty_companion.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swifty_companion.network.UserInfoDTO

class UserInfoViewModel: ViewModel() {

    var userInfo: MutableLiveData<UserInfoDTO>? = null

    init {
        userInfo = MutableLiveData()
    }


}