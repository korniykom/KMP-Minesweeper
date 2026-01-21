package com.korniykom.settings.data

import com.korniykom.data.storage.Storage
import com.korniykom.settings.domain.BoardSettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BoardSettingsRepositoryImpl(
    private val storage: Storage
) : BoardSettingsRepository {
    override val cols: Flow<Int> = storage.getAsFlow(BoardSettingsRepository.colsKey)
        .map { it ?: 10 }
    override val rows: Flow<Int> = storage.getAsFlow(BoardSettingsRepository.rowsKey)
        .map { it ?: 10 }

    override suspend fun updateCols(colNum: Int) {
        storage.writeValue(BoardSettingsRepository.colsKey, colNum)
    }

    override suspend fun updateRows(rowNum: Int) {
        storage.writeValue(BoardSettingsRepository.rowsKey, rowNum)
    }
}