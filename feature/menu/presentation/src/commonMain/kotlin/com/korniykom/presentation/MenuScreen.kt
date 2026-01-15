package com.korniykom.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MenuScreen(
    goToPlay:() -> Unit,
    goToHighScore: () -> Unit,
    goToSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        TextButton(
            onClick = goToPlay
        ) {
            Text("play")
        }
        TextButton(
            onClick = goToHighScore
        ) {
            Text("highscore")
        }
        TextButton(
            onClick = goToSettings
        ) {
            Text("settings")
        }
    }

}