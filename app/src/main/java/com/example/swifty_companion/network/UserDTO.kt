package com.example.swifty_companion.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoDTO(
    @SerialName("id")
    val id: Int,
    @SerialName("email")
    val email: String,
    @SerialName("login")
    val login: String,
    @SerialName("url")
    val url: String,
    @SerialName("phone")
    val phone: String,
    @SerialName("displayname")
    val displayName: String,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("correction_point")
    val correctionPoint: String,
    @SerialName("wallet")
    val wallet: String,
    @SerialName("cursus_users")
    val cursusUsers: List<CursusUsersDTO>,
    @SerialName("projects_users")
    val projectsUsers: List<ProjectsUsersDTO>,
    @SerialName("achievements")
    val achievements: List<AchievementsDTO>,
    @SerialName("campus")
    val campus: List<CampusDTO>
)

@Serializable
data class CursusUsersDTO(
    @SerialName("level")
    val level: String,
    @SerialName("skills")
    val skills: List<SkillsDTO>,
    @SerialName("begin_at")
    val beginAt: String?,
    @SerialName("cursus")
    val cursus: CursusDTO
)

@Serializable
data class SkillsDTO(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("level")
    val level: String,
)

@Serializable
data class CursusDTO(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)

@Serializable
data class ProjectsUsersDTO(
    @SerialName("final_mark")
    val finalMark: String?,
    @SerialName("status")
    val status: String,
    @SerialName("project")
    val project: ProjectDTO,
    @SerialName("cursus_ids")
    val cursusIds: List<Int>,
    @SerialName("marked_at")
    val markedAt: String?,
)

@Serializable
data class ProjectDTO(
    @SerialName("name")
    val name: String,
    @SerialName("parent_id")
    val parentId: String?
)

@Serializable
data class CampusDTO(
    @SerialName("country")
    val country: String,
    @SerialName("city")
    val city: String,
)

@Serializable
data class AchievementsDTO(
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String,
    @SerialName("image")
    val image: String,
)