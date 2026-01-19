package com.korniykom.settings.data.usecases

import com.korniykom.settings.domain.UsernameRepository
import kotlinx.coroutines.flow.Flow

class GetUsernameAsFlowUseCase(
    private val usernameRepository: UsernameRepository
) {
    operator fun invoke(): Flow<String> = usernameRepository.username
}