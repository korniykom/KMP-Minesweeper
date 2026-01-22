package com.korniykom.minesweeper.settings.data

import com.korniykom.minesweeper.core.data.storage.Storage
import com.korniykom.minesweeper.settings.domain.UsernameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UsernameRepositoryImpl(
    private val storage: Storage
) : UsernameRepository {
    override val username: Flow<String> = storage.getAsFlow(UsernameRepository.UsernameKey)
        .map { it.orEmpty() }

    override suspend fun updateUsername(username: String) {
        storage.writeValue(UsernameRepository.UsernameKey, username)
    }
}