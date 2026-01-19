package com.korniykom.minesweeper

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.korniykom.presentation.Highscores
import com.korniykom.presentation.Menu
import com.korniykom.settings.presentation.Settings
import com.korniykom.presentation.highscoresRoutes
import com.korniykom.presentation.menuRoutes
import com.korniykom.settings.presentation.settingsRoutes
import org.koin.compose.KoinApplication
import org.koin.core.module.Module

@Composable
@Preview
fun App(
    platformModule: Module = Module()
) {
    KoinApplication(
        application = {
            modules(appModule, platformModule)
        }
    ) {
        MaterialTheme {

            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Menu
            ) {
                menuRoutes(
                    goToPlay = {},
                    goToHighScore = {
                        navController.navigate(Highscores)
                    },
                    goToSettings = {
                        navController.navigate(Settings)
                    }
                )
                highscoresRoutes()
                settingsRoutes()
            }
        }
    }

}