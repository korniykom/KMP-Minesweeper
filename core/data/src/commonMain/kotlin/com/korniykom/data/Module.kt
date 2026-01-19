package com.korniykom.data

import org.koin.dsl.module
import org.koin.core.module.Module

val coreDataModule = module {
    includes(platformModule)
}

internal expect val platformModule: Module