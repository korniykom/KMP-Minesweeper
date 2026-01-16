package com.korniykom.presentation

sealed class TileState {
    data class Hidden(val flagged: Boolean): TileState()
    sealed class Revealed: TileState() {
        data object Mine: Revealed()
        data class Number(val number: Int?): Revealed()
    }
}