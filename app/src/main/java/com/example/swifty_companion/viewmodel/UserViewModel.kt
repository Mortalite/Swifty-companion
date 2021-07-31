package com.example.swifty_companion.viewmodel

import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.swifty_companion.adapter.CursusAdapter
import com.example.swifty_companion.adapter.InformationAdapter
import com.example.swifty_companion.adapter.ProjectsAdapter
import com.example.swifty_companion.adapter.SkillsAdapter
import com.example.swifty_companion.network.UserInfoDTO

class UserViewModel: ViewModel() {

    data class onClickButtonSettings(
        var position: Int = 0,
        var id: Int = 0,
        val colorInit: Int = Color.WHITE,
        val colorSelected: Int = Color.rgb(143, 188, 219)
    )

    var userInfo: MutableLiveData<UserInfoDTO>? = null
    var informationAdapter: MutableLiveData<InformationAdapter>? = null
    var cursusAdapter: MutableLiveData<CursusAdapter>? = null
    var skillsAdapter: MutableLiveData<SkillsAdapter>? = null
    var projectsAdapter: MutableLiveData<ProjectsAdapter>? = null
    var buttonSettings: onClickButtonSettings? = null

    init {
        userInfo = MutableLiveData()
        informationAdapter = MutableLiveData()
        cursusAdapter = MutableLiveData()
        skillsAdapter = MutableLiveData()
        projectsAdapter = MutableLiveData()
        buttonSettings = onClickButtonSettings()
    }


}