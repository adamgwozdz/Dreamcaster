package com.agiletech.dreamcaster.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.agiletech.dreamcaster.data.entities.Dream
import kotlinx.coroutines.flow.Flow

@Dao
interface DreamsDao {

    /**
     * Observes list of dreams.
     *
     * @return all dreams
     */
    @Query("SELECT * FROM Dreams")
    fun observeDreams(): Flow<List<Dream>>

    /**
     * Selects all dreams from the dreams table.
     *
     * @return all dreams
     */
    @Query("SELECT * FROM Dreams")
    fun getDreams(): List<Dream>

    /**
     * Insert a dream in the database. If the dream already exists, replace it.
     *
     * @param dream the dream to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDream(dream: Dream)
}