package com.korniykom.play.presentation

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val playPresentationModule = module {
    viewModelOf(::PlayViewModel)
}
