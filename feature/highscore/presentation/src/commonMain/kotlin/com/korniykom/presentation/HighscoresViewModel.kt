package com.korniykom.presentation

import androidx.lifecycle.ViewModel
import com.korniykom.data.storage.Storage
import com.korniykom.highscores.domain.HighscoresRepository


internal class HighscoresViewModel(
    private val storage: Storage
): ViewModel() {

    val highscoresFlow = storage.getAsFlow(HighscoresRepository.highScore)


    val title = "Highscores"

}