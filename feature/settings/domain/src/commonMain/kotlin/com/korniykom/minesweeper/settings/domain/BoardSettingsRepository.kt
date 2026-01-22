package com.korniykom.minesweeper.settings.domain

import com.korniykom.minesweeper.core.data.storage.Storage
import kotlinx.coroutines.flow.Flow

interface BoardSettingsRepository {
    val cols: Flow<Int>
    val rows: Flow<Int>

    suspend fun updateCols(colNum: Int)
    suspend fun updateRows(rowNum: Int)

    data object colsKey : Storage.Key.IntKey("columns", 10)
    data object rowsKey : Storage.Key.IntKey("rows", 10)


}
