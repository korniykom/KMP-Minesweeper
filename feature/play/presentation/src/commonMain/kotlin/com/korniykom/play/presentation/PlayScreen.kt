package com.korniykom.play.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.korniykom.minesweeper.presentation.components.Board

@Composable
fun PlayScreen(
    viewModel: PlayViewModel,
    modifier: Modifier = Modifier
) {
    val board by viewModel.userBoard.collectAsState()
    val remainingSeconds by viewModel.remainTimerSeconds.collectAsState()
    val bombsOnField by viewModel.bombNumber.collectAsState()
    val bombChecked by viewModel.bombChecked.collectAsState()
    val remainingBombs = bombsOnField - bombChecked
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Board(
            tileState = board,
            onClick = viewModel::onClick,
            onLongClick = viewModel::onLongClick,
            emoji = "\uD83D\uDE0E",
            remainingBombs = remainingBombs.toString(),
            remainingSeconds = remainingSeconds.toString()
        )
    }

}