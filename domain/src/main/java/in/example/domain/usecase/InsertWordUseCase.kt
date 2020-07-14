package `in`.example.domain.usecase

import `in`.example.domain.base.UseCase
import `in`.example.domain.entity.WordUIModel
import `in`.example.domain.repository.WordRepository

class InsertWordUseCase(private val wordRepository: WordRepository) : UseCase<WordUIModel> {
    override suspend fun execute(param: WordUIModel, callback: UseCase.Callback) {
        wordRepository.insertWord(param)
    }
}