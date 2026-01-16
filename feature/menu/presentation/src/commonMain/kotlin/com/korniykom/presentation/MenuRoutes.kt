package com.korniykom.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object Menu

fun NavGraphBuilder.menuRoutes(
    goToPlay:() -> Unit,
    goToHighScore: () -> Unit,
    goToSettings: () -> Unit,
) {
    composable<Menu> {
        MenuScreen(
            goToPlay = goToPlay,
            goToHighScore = goToHighScore,
            goToSettings = goToSettings,
            modifier = Modifier.fillMaxSize()
        )
    }
}