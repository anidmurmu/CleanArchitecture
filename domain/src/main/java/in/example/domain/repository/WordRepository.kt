package `in`.example.domain.repository

import `in`.example.domain.entity.Result
import `in`.example.domain.entity.WordUIModel
import androidx.lifecycle.LiveData

interface WordRepository {
    suspend fun insertWord(word: WordUIModel): Result<Void>

    suspend fun deleteWord(word: WordUIModel)

    suspend fun deleteAllWords()

    suspend fun getAllWords(): LiveData<List<WordUIModel>>
}