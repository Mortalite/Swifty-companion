package com.example.swifty_companion.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilterByLoginDTO(
    @SerialName("id")
    val id: Long,
    @SerialName("login")
    val login: String,
    @SerialName("url")
    val url: String,
/*    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String,*/
)
