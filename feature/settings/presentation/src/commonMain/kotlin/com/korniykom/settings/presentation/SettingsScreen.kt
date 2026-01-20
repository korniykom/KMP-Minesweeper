package com.korniykom.settings.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    modifier: Modifier = Modifier
) {
    val cols by viewModel.colsFlow.collectAsState(initial = 10)
    val rows by viewModel.rowsFlow.collectAsState(initial = 10)

    var colText by remember(cols) {
        mutableStateOf(cols.toString())
    }
    var rowText by remember(rows) {
        mutableStateOf(rows.toString())
    }
    Column(
        modifier = modifier.fillMaxSize()
    ) {

        OutlinedTextField(
            value = rowText,
            onValueChange = { newRows ->
                rowText = newRows
                newRows.toIntOrNull()?.let {viewModel.updateRows(it)}
            },
            label = { Text("rows") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = colText,
            onValueChange = { newCols ->
                colText = newCols
                newCols.toIntOrNull()?.let {viewModel.updateCols(it)}
            },
            label = { Text("columns") },
            modifier = Modifier.fillMaxWidth()
        )

    }
}