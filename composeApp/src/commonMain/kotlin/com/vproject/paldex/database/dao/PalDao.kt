package com.vproject.paldex.database.dao

import com.vproject.paldex.PalEntity
import com.vproject.paldex.database.PalDatabase
import com.vproject.paldex.paldexDispatchers
import kotlinx.coroutines.withContext

class PalDao(private val palDatabase: PalDatabase) {
    private val query get() = palDatabase.palEntityQueries

    suspend fun selectAllByPage(page: Long) = withContext(paldexDispatchers.io) {
        query.selectAllByPage(page = page).executeAsList()
    }

    suspend fun insert(pokemonEntity: PalEntity) = withContext(paldexDispatchers.io) {
        query.insert(pokemonEntity)
    }
}