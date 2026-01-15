package com.korniykom.minesweeper

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.korniykom.presentation.MenuScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    KoinApplication(
        application = {
            modules(appModule)
        }
    ) {
        MaterialTheme {
            MenuScreen(
                goToPlay = {},
                goToHighScore = {},
                goToSettings = {},
            )

        }
    }

}