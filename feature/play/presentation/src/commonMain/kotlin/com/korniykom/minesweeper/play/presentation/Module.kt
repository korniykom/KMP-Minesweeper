package com.korniykom.minesweeper.play.presentation

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val playPresentationModule = module {
    viewModelOf(::PlayViewModel)
}
