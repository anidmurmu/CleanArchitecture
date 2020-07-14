package `in`.example.data.repository

import `in`.example.data.database.model.WordEntity
import `in`.example.domain.Mapper
import `in`.example.domain.entity.WordUIModel

class WordEntityToWordUiModel : Mapper<WordUIModel, WordEntity> {
    override fun mapFrom(type: WordUIModel): WordEntity {
        return WordEntity(type.word)
    }
}