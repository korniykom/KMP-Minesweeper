package com.korniykom.minesweeper.settings.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            OutlinedTextField(
                value = rowText,
                onValueChange = { newRows ->
                    val filtered = newRows.filter { it.isDigit() }
                    rowText = filtered

                    filtered.toIntOrNull()?.let { number ->
                        val clamped = number.coerceIn(2..20)
                        viewModel.updateRows(clamped)
                    }
                },
                label = { Text("rows") },
                modifier = Modifier.width(150.dp)
            )

            Spacer(
                modifier = Modifier.width(40.dp)
            )

            OutlinedTextField(
                value = colText,
                onValueChange = { newCols ->
                    val filtered = newCols.filter { it.isDigit() }
                    colText = filtered

                    filtered.toIntOrNull()?.let { number ->
                        val clamped = number.coerceIn(2..20)
                        viewModel.updateCols(clamped)
                    }
                },
                label = { Text("columns") },
                modifier = Modifier.width(150.dp)
            )

        }
    }
}