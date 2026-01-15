package com.korniykom.minesweeper

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform