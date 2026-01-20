package com.korniykom.play.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korniykom.data.storage.Storage
import com.korniykom.minesweeper.presentation.components.BoardState
import com.korniykom.minesweeper.presentation.components.TileState
import com.korniykom.settings.domain.BoardSettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class PlayViewModel(
    private val storage: Storage
) : ViewModel() {
    private val _boardState = MutableStateFlow<BoardState>(emptyList())
    val boardState = _boardState.asStateFlow()

    private val rowsFlow = storage.getAsFlow(BoardSettingsRepository.rowsKey);
    private val colsFlow = storage.getAsFlow(BoardSettingsRepository.colsKey)
    init {
        viewModelScope.launch {
            val rows = rowsFlow.firstOrNull() ?: 10
            val cols = colsFlow.firstOrNull() ?: 10
            initBoard(rows, cols)
        }
    }

    private fun initBoard(row: Int, col: Int) {
        _boardState.value = List(row) {
            List(col) {
                TileState.Hidden(flagged = true)
            }
        }
    }
}