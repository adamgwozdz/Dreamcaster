package com.agiletech.dreamcaster.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.agiletech.dreamcaster.data.database.dao.DreamsDao
import com.agiletech.dreamcaster.data.database.dao.TagsDao
import com.agiletech.dreamcaster.data.database.entity.DreamEntity
import com.agiletech.dreamcaster.data.database.entity.TagEntity

@Database(entities = [DreamEntity::class, TagEntity::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun dreamsDao(): DreamsDao

    abstract fun tagsDao(): TagsDao
}