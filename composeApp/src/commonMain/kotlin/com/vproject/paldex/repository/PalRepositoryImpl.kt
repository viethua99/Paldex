package com.vproject.paldex.repository

import com.vproject.paldex.database.dao.PalDao
import com.vproject.paldex.database.dao.PalInfoDao
import com.vproject.paldex.model.Pal
import com.vproject.paldex.model.PalInfo
import com.vproject.paldex.network.PaldexApi
import com.vproject.paldex.paldexDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class PalRepositoryImpl(private val paldexApi: PaldexApi, private val palDao: PalDao, private val palInfoDao: PalInfoDao): PalRepository {
    /**
     * Method to request getting detail information of a Pal.
     *
     * @param name the name of the Pal.
     *
     * @return detail information of requested Pal
     */
    override suspend fun getPalDetail(name: String): Result<PalInfo> = withContext(paldexDispatchers.io) {
        return@withContext try {
            val localPal = palInfoDao.getOnePalInfoByName(name = name)

            if (localPal == null) {
                val response = paldexApi.getPalDetail(name)
                val content = response.content[0]
                palInfoDao.insert(content.toPalInfo().toPalInfoEntity())
                Result.success(content.toPalInfo())
            } else {
                Result.success(localPal.toPalInfo())
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Method to request getting list of information of all Pals.
     *
     * @param page the page number of Pal list.
     *
     * @return List contains all Pal information.
     */
    override suspend fun getPalList(page: Long): Result<List<Pal>> = withContext(paldexDispatchers.io) {
        return@withContext try {
            val localPalList = palDao.selectAllByPage(page)
            if (localPalList.isEmpty()) {
                val response = paldexApi.getPalList(page)
                response.content.forEach { content ->
                    palDao.insert(content.toPalEntity(page))
                }
                Result.success(palDao.selectAllByPage(page).map { it.toPal() })
            } else {
                Result.success(localPalList.map { it.toPal() })
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    /**
     * Method to request getting list of favorite Pals.
     *
     * @return List contains all favorite Pal information.
     */
    override suspend fun getFavoritePalList(): Flow<List<Pal>> {
        return palInfoDao.getAllFavoritePalInfo().map { palInfoList ->
            palInfoList.map { it.toPal() }
        }
    }

    /**
     * Method to update favorite status of a Pal.
     * @param name the name of the Pal.
     * @param isFavorite the favorite status of the Pal.
     *
     * @return None.
     */
    override suspend fun setPalInfoFavorite(name: String, isFavorite: Boolean) {
        palInfoDao.updateIsFavorite(name, isFavorite)
    }
}