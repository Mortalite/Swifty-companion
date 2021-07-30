package com.example.swifty_companion.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swifty_companion.adapter.CursusAdapter
import com.example.swifty_companion.adapter.SkillsAdapter
import com.example.swifty_companion.network.UserInfoDTO

class UserViewModel: ViewModel() {

    var userInfo: MutableLiveData<UserInfoDTO>? = null
    var cursusAdapter: MutableLiveData<CursusAdapter>? = null
    var skillsAdapter: MutableLiveData<SkillsAdapter>? = null

    init {
        userInfo = MutableLiveData()
        cursusAdapter = MutableLiveData()
        skillsAdapter = MutableLiveData()
    }


}