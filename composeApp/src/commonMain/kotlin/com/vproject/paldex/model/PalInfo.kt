package com.vproject.paldex.model

import kotlinx.serialization.Serializable

@Serializable
data class PalInfo(
    val id: Long,
    val key: String,
    val image: String,
    val name: String,
    val wiki: String,
    val types: List<Type>,
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


@Serializable
data class Aura (
    val name: String,
    val description: String,
    val tech: String? = null
)

@Serializable
data class Maps (
    val day: String? = null,
    val night: String? = null
)

@Serializable
enum class Size(val value: String) {
    L("l"),
    M("m"),
    S("s"),
    Xl("xl"),
    Xs("xs");

    companion object {
        fun fromValue(value: String): Size = when (value) {
            "l"  -> L
            "m"  -> M
            "s"  -> S
            "xl" -> Xl
            "xs" -> Xs
            else -> throw IllegalArgumentException()
        }
    }
}

@Serializable
data class Skill (
    val level: Long,
    val name: String,
    val type: String,
    val cooldown: Long,
    val power: Long,
    val description: String
)

@Serializable
enum class Type(val value: String) {
    Dark("dark"),
    Dragon("dragon"),
    Electric("electric"),
    Fire("fire"),
    Grass("grass"),
    Ground("ground"),
    Ice("ice"),
    Neutral("neutral"),
    Water("water");

    companion object {
        fun fromValue(value: String): Type = when (value) {
            "dark"     -> Dark
            "dragon"   -> Dragon
            "electric" -> Electric
            "fire"     -> Fire
            "grass"    -> Grass
            "ground"   -> Ground
            "ice"      -> Ice
            "neutral"  -> Neutral
            "water"    -> Water
            else       -> throw IllegalArgumentException()
        }
    }
}

@Serializable
data class Stats (
    val hp: Long,
    val attack: Attack,
    val defense: Long,
    val speed: Speed,
    val stamina: Long,
    val support: Long,
    val food: Long
)

@Serializable
data class Attack (
    val melee: Long,
    val ranged: Long
)

@Serializable
data class Speed (
    val ride: Long,
    val run: Long,
    val walk: Long
)

@Serializable
data class Suitability (
    val type: String,
    val image: String,
    val level: Long
)