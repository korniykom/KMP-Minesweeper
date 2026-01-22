package com.korniykom.minesweeper

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.korniykom.minesweeper.play.presentation.Play
import com.korniykom.minesweeper.play.presentation.playRoutes
import com.korniykom.minesweeper.menu.presentation.Menu
import com.korniykom.minesweeper.menu.presentation.menuRoutes
import com.korniykom.minesweeper.settings.presentation.Settings
import com.korniykom.minesweeper.settings.presentation.settingsRoutes
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
                    goToPlay = { navController.navigate(Play) },
                    goToSettings = {
                        navController.navigate(Settings)
                    }
                )
                settingsRoutes()
                playRoutes(
                    navigateToMenu = {
                        navController.navigate(Menu)
                    })
            }
        }
    }

}