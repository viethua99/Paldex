package com.vproject.paldex.di

import com.vproject.paldex.network.KtorPaldexApi
import com.vproject.paldex.network.PaldexApi
import com.vproject.paldex.repository.PalRepositoryImpl
import com.vproject.paldex.repository.PalRepository
import com.vproject.paldex.presentation.screen.favorite.FavoriteModel
import com.vproject.paldex.presentation.screen.home.HomeModel
import com.vproject.paldex.presentation.screen.detail.DetailModel
import com.vproject.paldex.database.createDatabase
import com.vproject.paldex.database.dao.PalDao
import com.vproject.paldex.database.dao.PalInfoDao
import com.vproject.paldex.database.sqlDriverFactory
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module


expect fun platformModule(): Module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(coreModule() + platformModule())
    }
}

// called by iOS client
fun initKoin() = initKoin() {}


fun coreModule() = module {
    // Network Dependencies
    single {
        HttpClient {
            install(ContentNegotiation) {
                json( Json { ignoreUnknownKeys = true })
            }
//            install(HttpTimeout) {
//                requestTimeoutMillis = 60000
//                connectTimeoutMillis = 60000
//                socketTimeoutMillis = 60000
//            }
        }
    }

    single<PaldexApi> { KtorPaldexApi(client = get()) }

    // Repository Dependencies
    single<PalRepository> { PalRepositoryImpl(paldexApi = get(), palDao = get(), palInfoDao = get())}

    // Screen Model Dependencies
    factoryOf(::HomeModel)
    factoryOf(::FavoriteModel)
    factoryOf(::DetailModel)

    // Local Database Dependencies
    factory { sqlDriverFactory() }
    single { createDatabase(driver = get()) }
    single { PalDao(palDatabase = get()) }
    single { PalInfoDao(palDatabase = get()) }
}