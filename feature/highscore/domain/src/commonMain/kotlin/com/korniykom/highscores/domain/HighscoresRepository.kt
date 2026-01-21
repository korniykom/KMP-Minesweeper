package com.korniykom.highscores.domain

import com.korniykom.data.storage.Storage
import kotlinx.coroutines.flow.Flow

interface HighscoresRepository {
    val highscores: Flow<List<Pair<String, String>>>

    suspend fun updateHighscores(highscore: List<Pair<String, String>>)

    data object highScore: Storage.Key.ListKey("highscores", emptyList())

}