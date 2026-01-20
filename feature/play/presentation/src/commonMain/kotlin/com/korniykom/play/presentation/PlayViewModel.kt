package com.korniykom.play.presentation

import androidx.lifecycle.ViewModel
import com.korniykom.minesweeper.presentation.components.BoardState
import com.korniykom.minesweeper.presentation.components.TileState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PlayViewModel : ViewModel() {
    private val _boardState = MutableStateFlow<BoardState>(emptyList())
    val boardState = _boardState.asStateFlow()

    private val row = 10;
    private val col = 10;

    init {
        initBoard(row, col)
    }

    private fun initBoard(row: Int, col: Int) {
        _boardState.value = List(row) {
            List(col) {
                TileState.Hidden(flagged = true)
            }
        }
    }
}