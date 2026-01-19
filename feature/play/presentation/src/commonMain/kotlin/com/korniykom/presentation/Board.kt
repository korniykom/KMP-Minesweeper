package com.korniykom.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun Board(
    tileState: List<List<TileState>>,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(),
) {
    if (tileState.isNotEmpty()) {
        val boardHeight = remember { tileState.first().size }
        val boardWidth = remember { tileState.size }
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
                                textStyle = sizeAdjustedTextStyle,
                                state = tileState,
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

@Preview
@Composable
fun BoardPreview() {
    val allPossibleTileStates = getAllPossibleTilesStates()
    val tileStates: List<List<TileState>> = buildList {
        repeat(20) {
            add(buildList {
                repeat(15) {
                    add(allPossibleTileStates.random())
                }
            })
        }
    }
    Board(
        tileState = tileStates,
        modifier = Modifier
            .fillMaxSize()
    )
}


private fun getAllPossibleTilesStates(): Set<TileState> = setOf(
    TileState.Hidden(flagged = true),
    TileState.Hidden(flagged = false),
    TileState.Revealed.Mine,
    TileState.Revealed.Number(number = null),
    TileState.Revealed.Number(number = 1),
    TileState.Revealed.Number(number = 2),
    TileState.Revealed.Number(number = 3),
    TileState.Revealed.Number(number = 4),
    TileState.Revealed.Number(number = 5),
    TileState.Revealed.Number(number = 6),
    TileState.Revealed.Number(number = 7),
    TileState.Revealed.Number(number = 8),
    TileState.Revealed.Number(number = 9),
)
