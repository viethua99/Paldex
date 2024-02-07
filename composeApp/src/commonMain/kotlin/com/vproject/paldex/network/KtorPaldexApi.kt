package com.vproject.paldex.network

import com.vproject.paldex.network.response.PalResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType

/**
 * Ktor API declaration for Paldex Network API
 */
class KtorPaldexApi(private val client: HttpClient) : PaldexApi {
    companion object {
        private const val PALDEX_BASE_URL = "http://10.0.2.2:3000/"
//        private const val PALDEX_BASE_URL = "http://localhost:3000/"
    }

    /**
     * Method to request getting detail information of a Pal.
     *
     * @param name the name of the Pal.
     *
     * @return text to image response body.
     */
    override suspend fun getPalDetail(name: String): PalResponse {
        return requestHandler {
            client.get(PALDEX_BASE_URL) {
                url {
                    parameters.append("name", name)
                }
                contentType(ContentType.Application.Json)
            }
        }
    }

    /**
     * Method to request getting list of information of all Pals.
     *
     * @return queued image response body.
     */
    override suspend fun getPalList(page: Long): PalResponse {
        return requestHandler {
            client.get(PALDEX_BASE_URL) {
                url {
                    parameters.append("page", page.toString())
                    parameters.append("limit", "100")
                }
                contentType(ContentType.Application.Json)
            }
        }
    }
}