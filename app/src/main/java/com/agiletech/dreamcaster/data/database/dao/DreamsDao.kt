package com.agiletech.dreamcaster.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.agiletech.dreamcaster.data.database.entity.DreamEntity
import com.agiletech.dreamcaster.data.model.Dream
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
     * Insert a dream into the database. If the dream already exists, replace it.
     *
     * @param dream the dream to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDream(dream: DreamEntity)
}