package com.example.osrsdex.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Loadout::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun loadoutDAO(): LoadoutDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            //Check if instance is null, if it isn't we return the data
            //if it is we create the db
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "sqlite_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                //return our database instance
                instance

            }
        }

    }
}