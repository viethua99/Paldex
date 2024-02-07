package com.vproject.paldex.repository

import com.vproject.paldex.PalEntity
import com.vproject.paldex.PalInfoEntity
import com.vproject.paldex.model.Pal
import com.vproject.paldex.model.PalInfo
import com.vproject.paldex.model.Type
import com.vproject.paldex.network.response.Content
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

fun Content.toPalEntity(page: Long) = PalEntity(
    page = page,
    key = key,
    name = name,
    imageWiki = imageWiki
)

fun PalInfoEntity.toPal() = Pal(
    key = key,
    name = name,
    imageWiki = imageWiki
)

fun Content.toPalInfo() = PalInfo(
    id = id,
    key = key,
    image = image,
    name = name,
    wiki = wiki,
    types = types.map { Type.fromValue(it) },
    imageWiki = imageWiki,
    suitability = suitability,
    drops = drops,
    aura = aura,
    description = description,
    skills = skills,
    stats = stats,
    asset = asset,
    genus = genus,
    rarity = rarity,
    price = price,
    size = size,
    maps = maps,
    isFavorite = isFavorite
)

fun PalEntity.toPal() = Pal(
    page = page,
    key = key,
    name = name,
    imageWiki = imageWiki
)

fun PalInfo.toPalInfoEntity() = PalInfoEntity(
     id = id,
     key = key,
     image = image,
     name = name,
     wiki = wiki,
     types = Json.encodeToString(types),
     imageWiki = imageWiki,
     suitability = Json.encodeToString(suitability),
     drops = Json.encodeToString(drops),
     aura = Json.encodeToString(aura),
     description = description,
     skills = Json.encodeToString(skills),
     stats = Json.encodeToString(stats),
     asset = asset,
     genus = genus,
     rarity = rarity,
     price = price,
     size = size,
     maps = Json.encodeToString(maps),
    isFavorite = if (isFavorite) 1 else 0
)

fun PalInfoEntity.toPalInfo() = PalInfo(
    id = id,
    key = key,
    image = image,
    name = name,
    wiki = wiki,
    types = Json.decodeFromString(types),
    imageWiki = imageWiki,
    suitability = Json.decodeFromString(suitability),
    drops = Json.decodeFromString(drops),
    aura = Json.decodeFromString(aura),
    description = description,
    skills = Json.decodeFromString(skills),
    stats = Json.decodeFromString(stats),
    asset = asset,
    genus = genus,
    rarity = rarity,
    price = price,
    size = size,
    maps = Json.decodeFromString(maps),
    isFavorite = isFavorite == 1L
)

