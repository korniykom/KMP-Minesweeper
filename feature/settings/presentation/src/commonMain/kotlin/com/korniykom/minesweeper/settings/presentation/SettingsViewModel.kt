package com.korniykom.minesweeper.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korniykom.minesweeper.core.data.storage.Storage
import com.korniykom.minesweeper.settings.domain.BoardSettingsRepository
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val storage: Storage
) : ViewModel() {

    val colsFlow = storage.getAsFlow(BoardSettingsRepository.colsKey)
    val rowsFlow = storage.getAsFlow(BoardSettingsRepository.rowsKey)


    fun updateCols(cols: Int) {
        viewModelScope.launch {
            storage.writeValue(BoardSettingsRepository.colsKey, cols)
        }
    }

    fun updateRows(rows: Int) {
        viewModelScope.launch {
            storage.writeValue(BoardSettingsRepository.rowsKey, rows)
        }
    }
}

