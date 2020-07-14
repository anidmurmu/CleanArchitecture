package `in`.example.data.repository

import `in`.example.data.database.dao.WordDao
import `in`.example.domain.entity.WordUIModel
import `in`.example.domain.repository.WordRepository

class WordRepositoryImpl(private val wordDao: WordDao, private val mapper: WordEntityToWordUiModel) : WordRepository {

    override suspend fun insertWord(word: WordUIModel) {
        wordDao.insertWord(mapper.mapFrom(word))
    }

    override suspend fun deleteWord(word: WordUIModel) {
    }

    override suspend fun deleteAllWords() {
    }
}