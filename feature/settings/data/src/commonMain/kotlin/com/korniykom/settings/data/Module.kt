package com.korniykom.settings.data

import com.korniykom.settings.domain.UsernameRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val settingDataModule = module {
    singleOf(::UsernameRepositoryImpl) {
        bind<UsernameRepository>()
    }

}