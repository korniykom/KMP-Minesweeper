package com.korniykom.settings.data.usecases

import com.korniykom.settings.domain.UsernameRepository

class UpdateUsernameUseCase(
    private val usernameRepository: UsernameRepository
) {
    suspend operator fun invoke(username: String) {
        usernameRepository.updateUsername(username)
    }
}