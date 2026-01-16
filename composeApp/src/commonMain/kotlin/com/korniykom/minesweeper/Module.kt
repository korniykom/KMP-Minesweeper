package com.korniykom.minesweeper

import com.korniykom.presentation.highscoresPresentationModule
import org.koin.dsl.module

val appModule = module {
    includes(highscoresPresentationModule)
}