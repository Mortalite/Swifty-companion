package com.example.swifty_companion.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swifty_companion.adapter.ProjectsAdapter
import com.example.swifty_companion.network.UserInfoDTO

class UserInfoViewModel: ViewModel() {

    var userInfo: MutableLiveData<UserInfoDTO>? = null
    var projectsAdapter: MutableLiveData<ProjectsAdapter>? = null

    init {
        userInfo = MutableLiveData()
        projectsAdapter = MutableLiveData()
    }


}