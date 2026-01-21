package com.korniykom.play.presentation

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korniykom.data.storage.Storage
import com.korniykom.minesweeper.presentation.components.BoardState
import com.korniykom.minesweeper.presentation.components.TileState
import com.korniykom.settings.domain.BoardSettingsRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Collections.emptySet
import kotlin.random.Random

class PlayViewModel(
    private val storage: Storage
) : ViewModel() {
    private val _boardState = MutableStateFlow<BoardState>(emptyList())
    private val _userBoard = MutableStateFlow<BoardState>(emptyList())
    private val _bombNumber = MutableStateFlow<Int>(-1)
    private val _bombChecked = MutableStateFlow<Int>(-1)
    private val _correctlyCheckedBombs = MutableStateFlow(0)
    private val _playerExploded = MutableStateFlow(false)
    val playerExploded = _playerExploded.asStateFlow()
    val boardState = _boardState.asStateFlow()
    val userBoard = _userBoard.asStateFlow()
    val bombNumber = _bombNumber.asStateFlow()
    val bombChecked = _bombChecked.asStateFlow()
    val correctlyCheckedBombs = _correctlyCheckedBombs.asStateFlow()

    private val countDownStartSeconds = 999

    private val _remainTimerSeconds = MutableStateFlow(countDownStartSeconds)
    val remainTimerSeconds = _remainTimerSeconds.asStateFlow()
    private var timerJob: Job? = null
    private val rowsFlow = storage.getAsFlow(BoardSettingsRepository.rowsKey);
    private val colsFlow = storage.getAsFlow(BoardSettingsRepository.colsKey)

    init {
        viewModelScope.launch {
            val rows = rowsFlow.firstOrNull() ?: 10
            val cols = colsFlow.firstOrNull() ?: 10
            _bombNumber.update { rows * cols / 10 }
            initBoard(rows, cols, bombNumber.value)
            initUserBoard(rows, cols)
        }
        startTimer()
    }

    fun explodePlayer() {
        _playerExploded.update { true }
        _userBoard.value = _userBoard.value.mapIndexed { rowIndex, boardRow ->
            boardRow.mapIndexed{ colIndex, tile ->
                if(_boardState.value[rowIndex][colIndex] is TileState.Revealed.Mine) {
                    TileState.Revealed.Mine
                } else {
                    tile
                }
            }
        }
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (remainTimerSeconds.value > 0) {
                delay(1000L)
                _remainTimerSeconds.update { it - 1 }
            }
        }
    }

    fun onGetFlagged(row: Int, col: Int) {
        _bombChecked.update { it + 1 }
        if(boardState.value[row][col] is TileState.Revealed.Mine) {
            _correctlyCheckedBombs.update { it + 1 }
        }
    }

    fun onGetUnFlagged(row: Int, col: Int) {
        _bombChecked.update { it - 1 }
        if(boardState.value[row][col] is TileState.Revealed.Mine) {
            _correctlyCheckedBombs.update { it - 1 }
        }
    }

    private fun stopTimer() {
        timerJob?.cancel()
    }

    fun resetTimer() {
        _remainTimerSeconds.update { countDownStartSeconds }
    }

    fun onClick(row: Int, col: Int) {
        val currentRealTile = _boardState.value[row][col]
        val currentUserTile = _userBoard.value[row][col]
        if(currentRealTile is TileState.Revealed.Mine) {
            explodePlayer()
        }
        else if (currentRealTile is TileState.Revealed.Number && currentRealTile.number == null) {
            recursivelyRevealEmptyTiles(row, col)
        } else if(currentUserTile is TileState.Revealed.Number) {
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

            neighbors.forEach { (r, c) ->
                recursivelyRevealEmptyTiles(r, c)
            }
        } else {
            _userBoard.value = _userBoard.value.mapIndexed { rowIndex, boardRow ->
                boardRow.mapIndexed { colIndex, tile ->
                    if (row == rowIndex && col == colIndex) {
                        _boardState.value[row][col]
                    } else {
                        tile
                    }
                }
            }
        }
    }


    private fun recursivelyRevealEmptyTiles(
        row: Int,
        col: Int,
        visitedTiles: MutableSet<Pair<Int, Int>> = mutableSetOf()
    ) {
        if (row !in _boardState.value.indices || col !in _boardState.value[0].indices) return
        val userTile = _userBoard.value[row][col]
        val realTile = _boardState.value[row][col]

        if (visitedTiles.contains(row to col)) return
        if (userTile !is TileState.Hidden) return
        if (realTile is TileState.Revealed.Mine) return


        visitedTiles += row to col

        _userBoard.value = _userBoard.value.mapIndexed { rowIndex, boardRow ->
            boardRow.mapIndexed { colIndex, tile ->
                if (rowIndex == row && colIndex == col) {
                    realTile
                } else {
                    tile
                }
            }
        }

        if (realTile is TileState.Revealed.Number && realTile.number == null) {
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
                recursivelyRevealEmptyTiles(row, col, visitedTiles)
            }
        }

    }


    fun onLongClick(row: Int, col: Int) {
        _userBoard.value = _userBoard.value.mapIndexed { rowIndex, boardRow ->
            boardRow.mapIndexed { colIndex, tile ->
                if (rowIndex == row && colIndex == col && tile is TileState.Hidden) {
                    if (tile.flagged) {
                        onGetUnFlagged(rowIndex, colIndex)
                    } else {
                        onGetFlagged(rowIndex, colIndex)
                    }
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
                if (rowIndex to columnIndex in bombPositions) {
                    TileState.Revealed.Mine
                } else if (number == 0) {
                    TileState.Revealed.Number(null)
                } else {
                    TileState.Revealed.Number(number)
                }

            }
        }
    }
}