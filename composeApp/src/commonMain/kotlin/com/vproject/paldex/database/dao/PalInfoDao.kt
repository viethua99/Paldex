package com.vproject.paldex.database.dao

import app.cash.sqldelight.coroutines.asFlow
import com.vproject.paldex.PalInfoEntity
import com.vproject.paldex.database.PalDatabase
import com.vproject.paldex.paldexDispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class PalInfoDao(
    private val palDatabase: PalDatabase
) {
    private val query get() = palDatabase.palInfoEntityQueries

    suspend fun getOnePalInfoByName(name: String) = withContext(paldexDispatchers.io) {
        query.selectPalInfoByName(name = name).executeAsOneOrNull()
    }

    suspend fun getAllFavoritePalInfo() = withContext(paldexDispatchers.io) {
        query.selectAllFavorite().asFlow().map { it.executeAsList() }
    }

    suspend fun insert(pokemonInfoEntity: PalInfoEntity) = withContext(paldexDispatchers.io) {
        query.insert(pokemonInfoEntity)
    }

    suspend fun updateIsFavorite(name: String, isFavorite: Boolean) = withContext(paldexDispatchers.io) {
        query.updateIsFavorite(
            isFavorite = if (isFavorite) 1 else 0,
            name = name
        )
    }
}