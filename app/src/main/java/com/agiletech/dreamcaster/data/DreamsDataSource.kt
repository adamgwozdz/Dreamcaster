package com.agiletech.dreamcaster.data

import com.agiletech.dreamcaster.data.entities.Dream
import kotlinx.coroutines.flow.Flow

interface DreamsDataSource {

    fun getDreamsStream(): Flow<Result<List<Dream>>>

    suspend fun getDreams(): Result<List<Dream>>

    suspend fun saveDream(dream: Dream)
}