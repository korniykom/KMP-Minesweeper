package com.korniykom.minesweeper.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.onClick
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HeaderRow(
    onRestartClick: () -> Unit,
    remainingSeconds: String,
    remainingBombs: String,
    emoji: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        BoxContainer(
            remainingSeconds,
        )
        BoxContainer(
            emoji,
            onClick = onRestartClick

        )
        BoxContainer(
            remainingBombs,
        )
    }
}