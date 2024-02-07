package com.vproject.paldex.database

import app.cash.sqldelight.db.SqlDriver
import org.koin.core.scope.Scope

expect fun Scope.sqlDriverFactory(): SqlDriver

fun createDatabase(driver: SqlDriver): PalDatabase {
    val database = PalDatabase(
        driver = driver,
    )

    return database
}