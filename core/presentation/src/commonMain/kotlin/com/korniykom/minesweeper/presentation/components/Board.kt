package com.korniykom.minesweeper.presentation.components

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

typealias BoardState = List<List<TileState>>

@Composable
fun Board(
    emoji: String,
    remainingBombs: String,
    remainingSeconds: String,
    onClick: (row: Int, col: Int) -> Unit,
    onLongClick: (row: Int, col: Int) -> Unit,
    onRestartClick: () -> Unit,
    tileState: BoardState,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(),
) {
    if (tileState.isEmpty()) return

    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        val rows = tileState.size
        val cols = tileState.first().size

        val tileSize = minOf(
            maxWidth / cols,
            maxHeight / (rows + 1)
        )

        val adjustedTextStyle = textStyle.copy(
            fontSize = tileSize.value.sp * 0.75f
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderRow(
                remainingSeconds = remainingSeconds,
                remainingBombs = remainingBombs,
                emoji = emoji,
                modifier = Modifier.width(tileSize * cols),
                onRestartClick = onRestartClick
            )

            Column {
                for (row in 0 until rows) {
                    Row {
                        for (col in 0 until cols) {
                            val state = tileState[row][col]

                            Tile(
                                state = state,
                                onClick = { onClick(row, col) },
                                onLongClick = { onLongClick(row, col) },
                                textStyle = adjustedTextStyle,
                                revealedBorderWidth = tileSize / 32,
                                hiddenBorderWidth = tileSize / 8,
                                modifier = Modifier.size(tileSize)
                            )
                        }
                    }
                }
            }
        }
    }
}