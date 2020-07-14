package `in`.example.domain.usecase

import `in`.example.domain.base.BaseUseCase
import `in`.example.domain.entity.Result
import `in`.example.domain.entity.WordUIModel

interface InsertWordUseCase : BaseUseCase<WordUIModel, Void> {
    override suspend fun invoke(param: WordUIModel): Result<Void>
}