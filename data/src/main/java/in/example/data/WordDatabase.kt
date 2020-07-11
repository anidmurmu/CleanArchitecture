package `in`.example.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

const val DATABASE_NAME = "word_database"

@Database(entities = [Word::class], version = 1, exportSchema = false)
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