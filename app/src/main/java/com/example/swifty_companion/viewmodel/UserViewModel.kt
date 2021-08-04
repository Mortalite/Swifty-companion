package com.example.swifty_companion.viewmodel

import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import com.example.swifty_companion.adapter.*
import com.example.swifty_companion.network.*
import java.text.SimpleDateFormat
import java.util.*

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

    val cursusUsers: List<CursusUsersDTO>?
        get() {
            userInfo?.value?.cursusUsers.let {
                if (it.isNullOrEmpty())
                    return null
                return it
            }
        }

    val skills: List<SkillsDTO>?
        get() {
            cursusUsers?.get(0)?.skills.let {
                if (it.isNullOrEmpty())
                    return null
                return it
            }
        }

    val projectsUsers: List<ProjectsUsersDTO>?
        get() {
            userInfo?.value?.projectsUsers.let {
                if (it.isNullOrEmpty())
                    return null
                return it
            }
        }

    val achievements: List<AchievementsDTO>?
        get() {
            userInfo?.value?.achievements.let {
                if (it.isNullOrEmpty())
                    return null
                return it
            }
        }

    init {
        userInfo = MutableLiveData()
        informationAdapter = MutableLiveData()
        cursusAdapter = MutableLiveData()
        skillsAdapter = MutableLiveData()
        projectsAdapter = MutableLiveData()
        achievementsAdapter = MutableLiveData()
        resetButtonSettings()
    }

    fun getCourseIdByPosition(position: Int) = run {
        getCoursesSortedDate()?.get(position)?.cursus?.id
    }

    fun getCoursesSortedDate(): List<CursusUsersDTO>? = run {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)

        with(cursusUsers) {
            if (isNullOrEmpty())
                return null
            sortedWith((compareBy { sdf.parse(it.beginAt.toString()) }))
        }
    }

    fun getSkillsByPositionSortedLevel(position: Int) = run {
        getCoursesSortedDate()
            ?.get(position)
            ?.skills
            ?.sortedWith(compareBy(nullsFirst(), { it.level.toDoubleOrNull()  } ))
    }

    fun getSkillsByIdSortedLevel(id: Int) = run {
        cursusUsers?.find {
            it.cursus.id == id
        }
            ?.skills
            ?.sortedWith(compareBy(nullsFirst(), { it.level.toDoubleOrNull() } ))
    }

    fun getProjectsById(id: Int) = run {
        projectsUsers
            ?.filter {
                it.cursusIds.contains(id) //&&
//                it.project.parentId == null
            }
    }

    fun getProjectsByIdSortedDate(id: Int) = run {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)

        getProjectsById(id)
        ?.sortedBy { sdf.parse(it.createdAt) }
    }

    fun resetButtonSettings() {
        buttonSettings = ButtonSettings()
    }

}