package com.korniykom.play.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korniykom.data.storage.Storage
import com.korniykom.minesweeper.presentation.components.BoardState
import com.korniykom.minesweeper.presentation.components.TileState
import com.korniykom.settings.domain.BoardSettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlin.random.Random

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
            val numOfBombs = rows * cols / 10
            initBoard(rows, cols, numOfBombs)
        }
    }

    private fun initBoard(row: Int, col: Int, numOfBombs: Int) {
        val bombPositions = mutableSetOf<Pair<Int, Int>>()

        while (bombPositions.size < numOfBombs) {
            bombPositions += Random.nextInt(row) to Random.nextInt(col)
        }
        _boardState.value = List(row) { rowIndex ->
            List(col) { columnIndex ->
                if (rowIndex to columnIndex in bombPositions) {
                    TileState.Revealed.Mine
                } else {
                    var number = 0
                    if ((columnIndex - 1 to rowIndex + 1 in bombPositions)) {
                        number++
                    }
                    if ((columnIndex + 1 to rowIndex + 1 in bombPositions)) {
                        number++
                    }
                    if ((columnIndex to rowIndex + 1 in bombPositions)) {
                        number++
                    }
                    if ((columnIndex + 1 to rowIndex + 1 in bombPositions)) {
                        number++
                    }
                    if ((columnIndex - 1 to rowIndex in bombPositions)) {
                        number++
                    }
                    if ((columnIndex + 1 to rowIndex in bombPositions)) {
                        number++
                    }
                    if ((columnIndex - 1 to rowIndex - 1 in bombPositions)) {
                        number++
                    }
                    if ((columnIndex to rowIndex - 1 in bombPositions)) {
                        number++
                    }
                    when (number) {
                        1 -> TileState.Revealed.Number(number)
                        2 -> TileState.Revealed.Number(number)
                        3 -> TileState.Revealed.Number(number)
                        4 -> TileState.Revealed.Number(number)
                        5 -> TileState.Revealed.Number(number)
                        6 -> TileState.Revealed.Number(number)
                        7 -> TileState.Revealed.Number(number)
                        8 -> TileState.Revealed.Number(number)
                        else -> TileState.Revealed.Number(null)
                    }
                }
            }
        }
    }
}