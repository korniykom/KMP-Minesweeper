package com.korniykom.minesweeper

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.korniykom.minesweeper.core.data.storage.createDataStore

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Minesweeper",
    ) {
        val dataStore = createDataStore()
        App()
    }
}