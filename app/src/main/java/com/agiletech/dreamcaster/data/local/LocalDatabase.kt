package com.agiletech.dreamcaster.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.agiletech.dreamcaster.data.entities.Dream

@Database(entities = [Dream::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

        abstract fun dreamsDao(): DreamsDao
}