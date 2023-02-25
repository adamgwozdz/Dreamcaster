package com.agiletech.dreamcaster.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.agiletech.dreamcaster.data.database.entity.TagEntity

@Dao
interface TagsDao {

    /**
     * Insert a tag into the database. If the tag already exists, replace it.
     *
     * @param tag the tag to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTag(tag: TagEntity)
}