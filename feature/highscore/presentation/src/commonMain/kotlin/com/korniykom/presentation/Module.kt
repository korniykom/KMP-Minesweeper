package com.korniykom.presentation

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val highscoresPresentationModule = module {
    viewModelOf(::HighscoresViewModel)
}