package com.korniykom.minesweeper.core.data.storage

import android.content.Context
import com.korniykom.minesweeper.core.data.storage.createDataStore
import com.korniykom.minesweeper.core.data.storage.dataStoreFileName

fun createDataStore(context: Context) = createDataStore(
    producePath = {
        context.filesDir.resolve(dataStoreFileName).absolutePath
    }
)