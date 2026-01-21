package com.korniykom.minesweeper.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
    tileState: List<List<TileState>>,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(),
) {
    if (tileState.isNotEmpty()) {
        val boardWidth = remember { tileState.first().size }
        val boardHeight = remember { tileState.size }
        BoxWithConstraints(
            contentAlignment = Alignment.Center,
            modifier = modifier
        ) {
            val requiredRatio = boardWidth / boardHeight.toFloat()
            val currentRatio = maxWidth / maxHeight
            val tileLength = if (requiredRatio > currentRatio) {
                maxWidth / boardWidth
            } else {
                maxHeight / boardHeight
            }

            val sizeAdjustedTextStyle = textStyle.copy(
                fontSize = tileLength.value.sp * 0.8
            )

            Column(
                modifier = Modifier.width(
                    minOf(
                        maxWidth,
                        maxHeight * (boardWidth / boardHeight.toFloat())
                    )
                )
            ) {
                HeaderRow(
                    remainingSeconds = remainingSeconds,
                    remainingBombs = remainingBombs,
                    emoji = emoji,
                )

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .width(tileLength * boardWidth)
                    .height(tileLength * boardHeight)
            ) {
                for (x in tileState.indices)
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                    ) {
                        for (y in tileState[x].indices) {
                            val tileState = tileState[x][y]
                            val revealedBorder = tileLength / 32
                            val hiddenBorder = tileLength / 8
                            Tile(
                                state = tileState,
                                onLongClick = { onLongClick(x, y) },
                                onClick = { onClick(x, y) },
                                textStyle = sizeAdjustedTextStyle,
                                revealedBorderWidth = revealedBorder,
                                hiddenBorderWidth = hiddenBorder,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                            )
                        }
                    }
            }
            }
        }
    }
}

