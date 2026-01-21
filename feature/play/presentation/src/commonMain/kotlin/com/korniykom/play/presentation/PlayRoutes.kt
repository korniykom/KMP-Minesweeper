package com.korniykom.play.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel


@Serializable
data object Play

fun NavGraphBuilder.playRoutes(
    navigateToMenu: () -> Unit
) {
    composable<Play> {
        val viewModel: PlayViewModel = koinViewModel()
        PlayScreen(
            viewModel = viewModel,
            navigateToMenu = navigateToMenu
        )
    }
}