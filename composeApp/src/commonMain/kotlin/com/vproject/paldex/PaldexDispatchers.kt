package com.vproject.paldex

import kotlinx.coroutines.CoroutineDispatcher

interface PaldexDispatchers {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}

expect val paldexDispatchers: PaldexDispatchers