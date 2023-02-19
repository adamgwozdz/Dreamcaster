package com.agiletech.dreamcaster.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.agiletech.dreamcaster.data.entities.Tag

@Dao
interface TagsDao {

    /**
     * Insert a tag into the database. If the tag already exists, replace it.
     *
     * @param tag the tag to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTag(tag: Tag)
}