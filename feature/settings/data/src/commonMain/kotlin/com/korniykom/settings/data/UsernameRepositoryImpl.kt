package com.korniykom.settings.data

import com.korniykom.data.storage.Storage
import com.korniykom.settings.domain.UsernameRepository
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