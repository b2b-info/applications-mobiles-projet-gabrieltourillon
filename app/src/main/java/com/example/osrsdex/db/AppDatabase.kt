package com.example.osrsdex.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.osrsdex.models.Loadout
import com.example.osrsdex.models.Player

@Database(entities = [Player::class,Loadout::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playerDAO(): PlayerDAO
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