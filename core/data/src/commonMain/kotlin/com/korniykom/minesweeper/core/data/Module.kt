package com.korniykom.minesweeper.core.data

import com.korniykom.minesweeper.core.data.storage.DataStoreStorage
import com.korniykom.minesweeper.core.data.storage.Storage
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val coreDataModule = module {
    includes(platformModule)
    singleOf(::DataStoreStorage) {
        bind<Storage>()
    }
}

internal expect val platformModule: Module