package com.korniykom.play.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.korniykom.minesweeper.presentation.components.Board

@Composable
fun PlayScreen(
    viewModel: PlayViewModel,
    navigateToMenu: () -> Unit,
    modifier: Modifier = Modifier
) {
    val board by viewModel.userBoard.collectAsState()
    val realBoard by viewModel.boardState.collectAsState()
    val remainingSeconds by viewModel.remainTimerSeconds.collectAsState()
    val bombsOnField by viewModel.bombNumber.collectAsState()
    val bombChecked by viewModel.bombChecked.collectAsState()
    val remainingBombs = bombsOnField - bombChecked
    val correctlyCheckedBombs by viewModel.correctlyCheckedBombs.collectAsState()
    val userExploded by viewModel.playerExploded.collectAsState()
    val restartButtonState by viewModel.restartButtonState.collectAsState()
    val hiddenTiles by viewModel.hiddenTiles.collectAsState()
    val boardSize by viewModel.boardSize.collectAsState()


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Board(
            tileState = board,
            onClick = viewModel::onClick,
            onLongClick = viewModel::onLongClick,
            emoji = "${hiddenTiles}",
            remainingBombs = remainingBombs.toString(),
            remainingSeconds = remainingSeconds.toString(),
            onRestartClick = viewModel::resetBoard
        )
        if (correctlyCheckedBombs == bombsOnField || hiddenTiles == bombsOnField  ) {
            viewModel.stopTimer()
            AlertDialog(
                onDismissRequest = {},
                title = { Text("You Won") },
                text = { Text("Congratulations!") },
                confirmButton = {
                    Button(
                        onClick = {
                            navigateToMenu()
                        }
                    ) {
                        Text("Go to menu")
                    }
                }
            )
        }

        if (userExploded) {
            AlertDialog(
                onDismissRequest = navigateToMenu,
                title = { Text("You Lost") },
                text = { Text("Loser") },
                confirmButton = {
                    Button(
                        onClick = navigateToMenu
                    ) {
                        Text("Go to menu")
                    }
                }
            )
        }
    }

}