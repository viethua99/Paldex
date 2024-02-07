package com.vproject.paldex.network

import com.vproject.paldex.network.response.PalResponse

/**
 * API interface for Paldex Network API
 */
interface PaldexApi {
    /**
     * Method to request getting detail information of a Pal.
     *
     * @param name the name of the Pal.
     *
     * @return text to image response body.
     */
     suspend fun getPalDetail(name: String): PalResponse

    /**
     * Method to request getting list of information of all Pals.
     *
     * @return queued image response body.
     */
     suspend fun getPalList(page: Long): PalResponse
}