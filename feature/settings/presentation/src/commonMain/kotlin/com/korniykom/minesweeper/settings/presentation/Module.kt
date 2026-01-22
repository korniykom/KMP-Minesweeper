package com.korniykom.minesweeper.settings.presentation


import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val settingPresentationModule = module {
    viewModelOf(::SettingsViewModel)

}