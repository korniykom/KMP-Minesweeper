package com.korniykom.minesweeper

import com.korniykom.data.coreDataModule
import com.korniykom.play.presentation.playPresentationModule
import com.korniykom.settings.data.settingDataModule
import com.korniykom.settings.presentation.settingPresentationModule
import org.koin.dsl.module

val appModule = module {
    includes(coreDataModule)
    includes(settingPresentationModule)
    includes(settingDataModule)
    includes(playPresentationModule)
}