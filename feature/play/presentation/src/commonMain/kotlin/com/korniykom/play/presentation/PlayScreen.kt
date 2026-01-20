package com.korniykom.play.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.korniykom.minesweeper.presentation.components.Board

@Composable
fun PlayScreen(
    viewModel: PlayViewModel,
    modifier: Modifier = Modifier
) {
    val board by viewModel.userBoard.collectAsState()
    Board(
        tileState = board,
        onClick = {row, col ->
            viewModel.onClick(row, col)
        },
        onLongClick = { row, col ->
            viewModel.onLongClick(row, col)
        }
    )

}