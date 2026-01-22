package com.korniykom.minesweeper

import com.korniykom.minesweeper.core.data.coreDataModule
import com.korniykom.minesweeper.play.presentation.playPresentationModule
import com.korniykom.minesweeper.settings.data.settingDataModule
import com.korniykom.minesweeper.settings.presentation.settingPresentationModule
import org.koin.dsl.module

val appModule = module {
    includes(coreDataModule)
    includes(settingPresentationModule)
    includes(settingDataModule)
    includes(playPresentationModule)
}