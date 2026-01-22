package com.korniykom.minesweeper.core.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.korniykom.minesweeper.core.data.storage.createDataStore
import org.koin.dsl.module

internal actual val platformModule = module {
    single<DataStore<Preferences>> {
        createDataStore()
    }
}