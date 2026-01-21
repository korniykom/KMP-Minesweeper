package com.korniykom.play.presentation

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.korniykom.minesweeper.presentation.utils.LocalPadding

@Composable
fun Timer(
    currentTime: Int,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier
    ) {
        Text(
            text = currentTime.toString(),
            modifier = Modifier
                .padding(
                    horizontal = LocalPadding.current.small,
                    vertical = LocalPadding.current.tiny
                )
        )
    }
}

@Preview
@Composable
fun TimerPreview() {
    Timer(999)
}