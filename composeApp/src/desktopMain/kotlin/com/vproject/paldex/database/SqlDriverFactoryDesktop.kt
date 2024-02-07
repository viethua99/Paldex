package com.vproject.paldex.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.koin.core.scope.Scope
import java.io.File

actual fun Scope.sqlDriverFactory(): SqlDriver {
    val databasePath = File(System.getProperty("java.io.tmpdir"), "palDatabase.db")
    val driver = JdbcSqliteDriver(url = "jdbc:sqlite:${databasePath.path}")
    PalDatabase.Schema.create(driver)
    return driver
}