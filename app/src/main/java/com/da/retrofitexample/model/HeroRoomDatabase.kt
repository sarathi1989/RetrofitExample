package com.da.retrofitexample.model

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import kotlinx.coroutines.launch

@Database(entities = arrayOf(HeroEntity::class), version = 1, exportSchema = false)
abstract class HeroRoomDatabase: RoomDatabase() {

    abstract fun heroDao() : HeroDao

    companion object{

        @Volatile
        var INSTANCE : HeroRoomDatabase? = null
        @InternalCoroutinesApi
        fun getDatabase(context: Context,
        scope: CoroutineScope) : HeroRoomDatabase{

            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HeroRoomDatabase::class.java,
                    "hero_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(HeroDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class HeroDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.heroDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(heroDao: HeroDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            heroDao.deleteAllfromHeroEntity()

           /* var word = Word("Hello")
            wordDao.insert(word)
            word = Word("World!")
            wordDao.insert(word)*/
        }


    }
}