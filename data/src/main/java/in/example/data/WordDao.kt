package `in`.example.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: Word)

    @Delete
    suspend fun deleteWord(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAllWords()

    @Query("SELECT * FROM word_table")
    fun getAllWords(): LiveData<List<Word>>
}