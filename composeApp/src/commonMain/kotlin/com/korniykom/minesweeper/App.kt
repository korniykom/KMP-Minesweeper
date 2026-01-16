package com.korniykom.minesweeper

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.korniykom.presentation.Highscores
import com.korniykom.presentation.HighscoresScreen
import com.korniykom.presentation.HighscoresViewModel
import com.korniykom.presentation.Menu
import com.korniykom.presentation.MenuScreen
import com.korniykom.presentation.highscoresRoutes
import com.korniykom.presentation.menuRoutes
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel
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
            val viewModel: HighscoresViewModel = koinViewModel()

            val navController  = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Menu
            ) {
                menuRoutes(
                    goToPlay = {},
                    goToHighScore = {
                        navController.navigate(Highscores)
                    },
                    goToSettings = {}
                )
               highscoresRoutes()
            }
        }
    }

}