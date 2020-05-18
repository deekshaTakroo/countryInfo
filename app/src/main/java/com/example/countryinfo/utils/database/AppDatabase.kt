package com.example.countryinfo.utils.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Task::class], version = 1)
/*
@TypeConverters(Converters::class)
*/
abstract class AppDatabase :RoomDatabase() {
    abstract fun taskDao(): TaskDAO?

    companion object {
        @Volatile
        private var appDatabaseInstance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = appDatabaseInstance
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "country_info_db"
                ).build()
                appDatabaseInstance = instance
                return instance
            }
        }
    }
}