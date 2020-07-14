package `in`.example.domain.repository

import `in`.example.domain.entity.WordUIModel

interface WordRepository {
    suspend fun insertWord(word: WordUIModel)

    suspend fun deleteWord(word: WordUIModel)

    suspend fun deleteAllWords()

    //suspend fun getAllWords(): LiveData<List<WordUIModel>>
}