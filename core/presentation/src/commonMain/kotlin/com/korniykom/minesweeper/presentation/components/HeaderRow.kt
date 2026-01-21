package com.korniykom.minesweeper.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HeaderRow(
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
        )
        BoxContainer(
            remainingBombs,
        )
    }
}