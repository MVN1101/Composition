package com.mvn1101.composition.domain.usecases

import com.mvn1101.composition.domain.entity.Question
import com.mvn1101.composition.domain.repository.GameRepository

class GenerateQuestionUseCase(
    private val repository: GameRepository
    ) {

    operator fun invoke (muxSumValue: Int): Question {
        return repository.generateQuestion(muxSumValue, COUNT_OF_OPTIONS)
    }

    private companion object {
        private const val COUNT_OF_OPTIONS = 6
    }

}