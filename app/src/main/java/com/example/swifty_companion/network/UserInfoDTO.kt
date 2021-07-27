package com.example.swifty_companion.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoDTO(
    @SerialName("id")
    val id: Long,
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
    @SerialName("wallet")
    val wallet: String,
    @SerialName("cursus_users")
    val cursusUsers: List<CursusUsersDTO>
)

@Serializable
data class CursusUsersDTO(
    @SerialName("level")
    val level: String,
    @SerialName("skills")
    val skills: List<SkillsDTO>,
    @SerialName("cursus")
    val cursus: CursusDTO
)

@Serializable
data class SkillsDTO(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("level")
    val level: String,
)

@Serializable
data class CursusDTO(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String
)
