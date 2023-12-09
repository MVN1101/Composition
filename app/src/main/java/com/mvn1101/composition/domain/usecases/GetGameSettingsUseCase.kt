package com.mvn1101.composition.domain.usecases

import com.mvn1101.composition.domain.entity.GameSettings
import com.mvn1101.composition.domain.entity.Level
import com.mvn1101.composition.domain.repository.GameRepository

class GetGameSettingsUseCase (
    private val repository: GameRepository
        ) {

    operator fun invoke (level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}