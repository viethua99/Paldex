package com.vproject.paldex.model

import kotlinx.serialization.Serializable

@Serializable
data class Pal(
    val page: Long = 0,
    val name: String,
    val key: String,
    val imageWiki: String,
)