package com.korniykom.highscore.data

import com.korniykom.data.storage.Storage
import com.korniykom.highscores.domain.HighscoresRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HighscoresRepositoryImpl(
    private val storage: Storage
) : HighscoresRepository {
    override val highscores: Flow<List<Pair<String, String>>> = storage.getAsFlow(
        HighscoresRepository.highScore
    ).map {
        it ?: emptyList()

    }

    override suspend fun updateHighscores(highscore: List<Pair<String, String>>) {
        storage.writeValue(HighscoresRepository.highScore, highscore)
    }



}