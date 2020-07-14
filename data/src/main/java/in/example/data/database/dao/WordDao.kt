package `in`.example.data.database.dao

import `in`.example.data.database.model.WordEntity
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: WordEntity)

    @Delete
    suspend fun deleteWord(word: WordEntity)

    @Query("DELETE FROM word_table")
    suspend fun deleteAllWords()

    @Query("SELECT * FROM word_table")
    fun getAllWords(): LiveData<List<WordEntity>>
}