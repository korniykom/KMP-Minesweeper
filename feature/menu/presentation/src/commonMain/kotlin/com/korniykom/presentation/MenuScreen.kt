package com.korniykom.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.korniykom.minesweeper.presentation.utils.LocalDimensions
import com.korniykom.minesweeper.presentation.utils.LocalPadding

@Composable
internal fun MenuScreen(
    goToPlay: () -> Unit,
    goToSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    val buttonModifier = Modifier
        .padding(horizontal = LocalPadding.current.normal)
        .widthIn(max = LocalDimensions.current.maxWidthSmall)
        .fillMaxWidth()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        MenuButton(
            buttonText = "PLAY",
            onClick = goToPlay,
            modifier = buttonModifier
        )
        MenuButton(
            buttonText = "SETTINGS",
            onClick = goToSettings,
            modifier = buttonModifier
        )
    }

}