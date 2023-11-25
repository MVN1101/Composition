package com.mvn1101.composition.domain.repository

import com.mvn1101.composition.domain.entity.GameSettings
import com.mvn1101.composition.domain.entity.Level
import com.mvn1101.composition.domain.entity.Question

interface GameRepository {

    fun generateQuestion (
        maxSumValue: Int,
        countOfOptions: Int
    ) : Question

    fun getGameSettings(level: Level): GameSettings
}