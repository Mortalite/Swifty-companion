package com.example.swifty_companion.viewmodel

import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import com.example.swifty_companion.adapter.*
import com.example.swifty_companion.network.UserInfoDTO

class UserViewModel: ViewModel() {

    data class ButtonSettings(
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
    var achievementsAdapter: MutableLiveData<AchievementsAdapter>? = null
    var buttonSettings: ButtonSettings? = null

    init {
        userInfo = MutableLiveData()
        informationAdapter = MutableLiveData()
        cursusAdapter = MutableLiveData()
        skillsAdapter = MutableLiveData()
        projectsAdapter = MutableLiveData()
        achievementsAdapter = MutableLiveData()
        resetButtonSettings()
    }

    fun resetButtonSettings() {
        buttonSettings = ButtonSettings()
    }

}