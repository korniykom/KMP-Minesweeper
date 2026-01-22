package com.korniykom.minesweeper.settings.domain

import com.korniykom.minesweeper.core.data.storage.Storage
import kotlinx.coroutines.flow.Flow

interface UsernameRepository {

    val username: Flow<String>

    suspend fun updateUsername(username: String)

    data object UsernameKey : Storage.Key.StringKey("username", "Anonymous")

}