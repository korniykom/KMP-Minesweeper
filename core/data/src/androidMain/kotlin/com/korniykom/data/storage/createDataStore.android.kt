package com.korniykom.data.storage

import android.content.Context

fun createDataStore(context: Context) = createDataStore(
    producePath = {
        context.filesDir.resolve(dataStoreFileName).absolutePath
    }
)