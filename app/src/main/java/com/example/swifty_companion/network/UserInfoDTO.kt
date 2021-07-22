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
    @SerialName("displayname")
    val displayName: String,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("wallet")
    val wallet: Long,
    @SerialName("cursus_users")
    val cursusUsers: List<cursusUsersDTO>
)

@Serializable
data class cursusUsersDTO(
    @SerialName("level")
    val level: String,
    @SerialName("skills")
    val skills: List<skillsDTO>
)

@Serializable
data class skillsDTO(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("level")
    val level: String,
)

