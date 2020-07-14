package `in`.example.data.database

import `in`.example.data.database.dao.WordDao
import `in`.example.data.database.model.WordEntity
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

const val DATABASE_NAME = "word_database"

@Database(entities = [WordEntity::class], version = 1, exportSchema = false)
abstract class WordDatabase : RoomDatabase() {

    abstract fun getWordDao(): WordDao

    companion object {
        @Volatile
        private var INSTANCE: WordDatabase? = null
    }

    fun getDatabase(context: Context): WordDatabase? {
        if (INSTANCE == null) {
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
            }
        }
        return INSTANCE
    }
}