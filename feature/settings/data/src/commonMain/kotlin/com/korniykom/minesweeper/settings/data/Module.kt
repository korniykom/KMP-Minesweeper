package com.korniykom.minesweeper.settings.data

import com.korniykom.minesweeper.settings.domain.UsernameRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val settingDataModule = module {
    singleOf(::UsernameRepositoryImpl) {
        bind<UsernameRepository>()
    }

}