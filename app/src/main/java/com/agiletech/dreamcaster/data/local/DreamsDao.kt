package com.agiletech.dreamcaster.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.agiletech.dreamcaster.data.entities.Dream
import com.agiletech.dreamcaster.data.entities.relations.DreamWithTags
import kotlinx.coroutines.flow.Flow

@Dao
interface DreamsDao {

    /**
     * Observes list of dreams.
     *
     * @return all dreams
     */
    @Query("SELECT * FROM Dreams")
    fun observeDreams(): Flow<List<DreamWithTags>>

    /**
     * Insert a dream into the database. If the dream already exists, replace it.
     *
     * @param dream the dream to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDream(dream: Dream)
}