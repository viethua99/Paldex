package com.vproject.paldex.network

import com.vproject.paldex.paldexDispatchers
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.withContext

suspend inline fun <reified T> requestHandler(
    crossinline response: suspend () -> HttpResponse
): T = withContext(paldexDispatchers.io) {

    val result = try {
        response()
    } catch(e: IOException) {
        throw PaldexException(PaldexError.ServiceUnavailable)
    }

    when(result.status.value) {
        in 200..299 -> Unit
        in 400..499 -> throw PaldexException(PaldexError.ClientError)
        500 -> throw PaldexException(PaldexError.ServerError)
        else -> throw PaldexException(PaldexError.UnknownError)
    }

    return@withContext try {
        result.body()
    } catch(e: Exception) {
        throw PaldexException(PaldexError.ServerError)
    }
}
enum class PaldexError {
    ServiceUnavailable,
    ClientError,
    ServerError,
    UnknownError
}

class PaldexException(error: PaldexError): Exception(
    "Something goes wrong: $error"
)