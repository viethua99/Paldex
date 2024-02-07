package com.vproject.paldex.network.response

import com.vproject.paldex.model.Aura
import com.vproject.paldex.model.Maps
import com.vproject.paldex.model.Skill
import com.vproject.paldex.model.Stats
import com.vproject.paldex.model.Suitability
import kotlinx.serialization.Serializable

@Serializable
data class PalResponse (
    val content: List<Content>,
    val page: Long,
    val limit: Long,
    val count: Long,
    val total: Long
)

@Serializable
data class Content (
    val id: Long,
    val key: String,
    val image: String,
    val name: String,
    val wiki: String,
    val types: List<String>,
    val imageWiki: String,
    val suitability: List<Suitability>,
    val drops: List<String>,
    val aura: Aura,
    val description: String,
    val skills: List<Skill>,
    val stats: Stats,
    val asset: String,
    val genus: String,
    val rarity: Long,
    val price: Long,
    val size: String,
    val maps: Maps,
    val isFavorite: Boolean = false
)