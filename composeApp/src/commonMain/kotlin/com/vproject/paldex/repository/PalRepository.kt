package com.vproject.paldex.repository

import com.vproject.paldex.model.Pal
import com.vproject.paldex.model.PalInfo
import kotlinx.coroutines.flow.Flow

interface PalRepository {
    /**
     * Method to request getting detail information of a Pal.
     *
     * @param name the name of the Pal.
     *
     * @return detail information of requested Pal
     */
     suspend fun getPalDetail(name: String): Result<PalInfo>

    /**
     * Method to request getting list of information of all Pals.
     *
     * @return List contains all Pal information.
     */
     suspend fun getPalList(page: Long): Result<List<Pal>>

    /**
     * Method to request getting list of favorite Pals.
     *
     * @return List contains all favorite Pal information.
     */
    suspend fun getFavoritePalList(): Flow<List<Pal>>

    /**
     * Method to update favorite status of a Pal.
     * @param name the name of the Pal.
     * @param isFavorite the favorite status of the Pal.
     *
     * @return None.
     *
     */
    suspend fun setPalInfoFavorite(name: String, isFavorite: Boolean)
}