package com.korniykom.data

import com.korniykom.data.storage.DataStoreStorage
import com.korniykom.data.storage.Storage
import org.koin.dsl.module
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf

val coreDataModule = module {
    includes(platformModule)
    singleOf(::DataStoreStorage) {
        bind<Storage>()
    }
}

internal expect val platformModule: Module