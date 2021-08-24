package com.example.applearnenglish

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [VocaModel::class],version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun vocaDao() : VocaDAO

    companion object{
        @Volatile
        private var instance : AppDatabase? = null

        fun getInstance(context : Context) : AppDatabase{
            if(instance==null){
                instance = Room.databaseBuilder(context,AppDatabase::class.java,"NAME_DATABASE_RELEASE_PART1")
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}