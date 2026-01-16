package com.korniykom.presentation

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.korniykom.minesweeper.presentation.LocalColors
import com.korniykom.minesweeper.presentation.LocalDimensions
import minesweeper.feature.play.presentation.generated.resources.Res
import minesweeper.feature.play.presentation.generated.resources.mine
import org.jetbrains.compose.resources.painterResource

@Composable
fun Tile(
    state: TileState,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(Color.Gray)
    ) {
        when (state) {
            is TileState.Hidden -> {
                if (state.flagged) {
                    Icon(
                        imageVector = Icons.Filled.Flag,
                        tint = LocalColors.current.flag,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(fraction = 0.6f)
                    )
                }
            }

            TileState.Revealed.Mine -> {
                Icon(
                    painter = painterResource(Res.drawable.mine),
                    tint = LocalColors.current.mine,
                    contentDescription = null,
                )
            }

            is TileState.Revealed.Number -> {
                val color = when(state.number) {
                    1 -> LocalColors.current.one
                    2 -> LocalColors.current.two
                    3 -> LocalColors.current.three
                    4 -> LocalColors.current.four
                    5 -> LocalColors.current.five
                    6 -> LocalColors.current.six
                    7 -> LocalColors.current.seven
                    8 -> LocalColors.current.eight
                    9 -> LocalColors.current.nine
                    else -> LocalColors.current.background
                }
                state.number?.let { number ->
                    Text(
                        text = number.toString(),
                        textAlign = TextAlign.Center,
                        color = color,
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(
                                align = Alignment.Center,
                            )
                    )
                }
            }
        }
    }
}


@Composable
@Preview
fun TilePreview() {
    val states: Set<TileState> = getAllPossibleTilesStates()
    FlowRow {
        states.forEach { state ->
            Tile(
                state = state,
                modifier = Modifier.size(LocalDimensions.current.iconLarge)
            )
        }
    }
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
