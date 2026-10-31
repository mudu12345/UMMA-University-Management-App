package com.example.ummauniversityapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class UniversityDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: UniversityDatabase? = null

        fun getDatabase(context: Context): UniversityDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UniversityDatabase::class.java,
                    "umma_university_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}