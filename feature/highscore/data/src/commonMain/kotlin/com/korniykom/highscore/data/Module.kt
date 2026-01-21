package com.korniykom.highscore.data

import com.korniykom.highscores.domain.HighscoresRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val highscoreDataModule = module {
    singleOf(::HighscoresRepositoryImpl) {
        bind<HighscoresRepository>()
    }
}