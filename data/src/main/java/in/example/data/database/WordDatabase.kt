package `in`.example.data.database

import `in`.example.data.database.dao.WordDao
import `in`.example.data.database.model.WordEntity
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


const val DATABASE_NAME = "word_database"

@Database(entities = [WordEntity::class], version = 1, exportSchema = false)
abstract class WordDatabase : RoomDatabase() {

  abstract fun getWordDao(): WordDao

  companion object {
    @Volatile
    private var INSTANCE: WordDatabase? = null

    fun getDatabase(scope: CoroutineScope, context: Context): WordDatabase? {
      if (INSTANCE == null) {
        synchronized(this) {
          val instance = Room.databaseBuilder(
            context.applicationContext,
            WordDatabase::class.java,
            DATABASE_NAME
          ).addCallback(WordRoomDatabaseCallback(scope, INSTANCE))
            .build()
          INSTANCE = instance
        }
      }
      return INSTANCE
    }
  }

  private class WordRoomDatabaseCallback(
    private val scope: CoroutineScope,
    private val wordDatabase: WordDatabase?
  ) :
    RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
      // do something after database has been created
    }

    override fun onOpen(db: SupportSQLiteDatabase) {
      // do something every time database is open
      scope.launch {
        prepopulateDb(scope, wordDatabase)
      }
    }

    suspend fun prepopulateDb(scope: CoroutineScope, wordDatabase: WordDatabase?) {
      val words = listOf("apple", "orange", "grapes", "watermelon", "jack fruit")
      words.forEach {
        wordDatabase?.getWordDao()?.insertWord(WordEntity(it))
      }
    }
  }

  /*private class WordDatabaseCallback(
    private val scope: CoroutineScope
  ) : RoomDatabase.Callback() {

    override fun onOpen(db: SupportSQLiteDatabase) {
      super.onOpen(db)
      INSTANCE?.let { database ->
        scope.launch {
          var wordDao = database.wordDao()

          // Delete all content here.
          wordDao.deleteAll()

          // Add sample words.
          var word = Word("Hello")
          wordDao.insert(word)
          word = Word("World!")
          wordDao.insert(word)
        }
      }
    }
  }*/

  /*companion object {
    @Volatile
    private var INSTANCE: WordRoomDatabase? = null

    fun getDatabase(
      context: Context,
      scope: CoroutineScope
    ): WordRoomDatabase {
      // if the INSTANCE is not null, then return it,
      // if it is, then create the database
      return INSTANCE ?: synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          WordRoomDatabase::class.java,
          "word_database"
        )
          .addCallback(WordDatabaseCallback(scope))
          .build()
        INSTANCE = instance
        // return instance
        instance
      }
    }
  }*/
}