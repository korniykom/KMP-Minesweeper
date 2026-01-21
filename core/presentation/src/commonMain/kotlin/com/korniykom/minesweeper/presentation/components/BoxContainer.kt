package com.korniykom.minesweeper.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.onClick
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.korniykom.minesweeper.presentation.utils.LocalPadding

@Composable
fun BoxContainer(
    value: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    OutlinedCard(
        onClick = onClick
    ) {
        Box(
            modifier = modifier
                .padding(
                    horizontal = LocalPadding.current.small,
                    vertical = LocalPadding.current.tiny
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = value,

                )
        }
    }
}