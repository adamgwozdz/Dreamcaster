package com.agiletech.dreamcaster.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.agiletech.dreamcaster.data.entities.Dream
import com.agiletech.dreamcaster.data.entities.Tag

@Database(entities = [Dream::class, Tag::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun dreamsDao(): DreamsDao

    abstract fun tagsDao(): TagsDao
}