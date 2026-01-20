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
    private val _userBoard = MutableStateFlow<BoardState>(emptyList())
    val boardState = _boardState.asStateFlow()
    val userBoard = _userBoard.asStateFlow()

    private val rowsFlow = storage.getAsFlow(BoardSettingsRepository.rowsKey);
    private val colsFlow = storage.getAsFlow(BoardSettingsRepository.colsKey)

    init {
        viewModelScope.launch {
            val rows = rowsFlow.firstOrNull() ?: 10
            val cols = colsFlow.firstOrNull() ?: 10
            val numOfBombs = rows * cols / 10
            initBoard(rows, cols, numOfBombs)
            initUserBoard(rows, cols)
        }
    }

    fun onClick(row: Int, col: Int) {
        val currentRealTile = _boardState.value[row][col]
        val currentUserTile = _userBoard.value[row][col]
        if(currentRealTile is TileState.Revealed.Number && currentRealTile.number == null) {
            recursivelyRevealEmptyTiles(row, col)
        } else {
            _userBoard.value = _userBoard.value.mapIndexed { rowIndex, boardRow ->
                boardRow.mapIndexed { colIndex, tile ->
                    if(row == rowIndex && col == colIndex) {
                        _boardState.value[row][col]
                    } else {
                        tile
                    }
                }
            }
        }
    }

    private fun recursivelyRevealEmptyTiles(row: Int, col: Int, visitedTiles: MutableSet<Pair<Int, Int>> = mutableSetOf()) {
        if(row !in _boardState.value.indices || col !in _boardState.value[0].indices) return
        val userTile = _userBoard.value[row][col]
        val realTile = _boardState.value[row][col]

        if(visitedTiles.contains(row to col)) return
        if(userTile !is TileState.Hidden) return

        visitedTiles += row to col

        _userBoard.value = _userBoard.value.mapIndexed { rowIndex, boardRow ->
            boardRow.mapIndexed { colIndex, tile ->
                if(rowIndex == row && colIndex == col) {
                    realTile
                } else {
                    tile
                }
            }
        }

        if(realTile is TileState.Revealed.Number && realTile.number == null) {
            val neighbors = listOf(
                row - 1 to col - 1,
                row - 1 to col,
                row - 1 to col + 1,
                row to col - 1,
                row to col + 1,
                row + 1 to col - 1,
                row + 1 to col,
                row + 1 to col + 1
            )
            neighbors.forEach { (row, col) ->
                recursivelyReviewEmptyTiles(row, col, visitedTiles)
            }
        }

    }
    fun onLongClick(row: Int, col: Int) {
        _userBoard.value = _userBoard.value.mapIndexed { rowIndex, boardRow ->
            boardRow.mapIndexed { colIndex, tile ->
                if (rowIndex == row && colIndex == col && tile is TileState.Hidden) {
                    TileState.Hidden(flagged = !tile.flagged)
                } else {
                    tile
                }
            }
        }
    }

    private fun initUserBoard(row: Int, col: Int) {
        _userBoard.value = List(row) {
            List(col) {
                TileState.Hidden(flagged = false)
            }
        }
    }

    private fun initBoard(row: Int, col: Int, numOfBombs: Int) {
        val bombPositions = mutableSetOf<Pair<Int, Int>>()


        while (bombPositions.size < numOfBombs) {
            bombPositions += Random.nextInt(row) to Random.nextInt(col)
        }
        _boardState.value = List(row) { rowIndex ->
            List(col) { columnIndex ->

                val neighbors = listOf(
                    rowIndex - 1 to columnIndex - 1,
                    rowIndex - 1 to columnIndex,
                    rowIndex - 1 to columnIndex + 1,
                    rowIndex to columnIndex - 1,
                    rowIndex to columnIndex + 1,
                    rowIndex + 1 to columnIndex - 1,
                    rowIndex + 1 to columnIndex,
                    rowIndex + 1 to columnIndex + 1
                )
                val number = neighbors.count { it in bombPositions }
                if (number == 0) {
                    TileState.Revealed.Number(null)
                } else if (rowIndex to columnIndex in bombPositions) {
                    TileState.Revealed.Mine
                } else {
                    TileState.Revealed.Number(number)
                }

            }
        }
    }
}