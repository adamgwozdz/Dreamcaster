package com.agiletech.dreamcaster.domain.repository

import com.agiletech.dreamcaster.data.entities.Dream
import com.agiletech.dreamcaster.data.Result
import kotlinx.coroutines.flow.Flow

interface DreamsRepository {

    fun getDreamsStream(): Flow<Result<List<Dream>>>

    suspend fun getDreams(): Result<List<Dream>>

    suspend fun saveDream(dream: Dream)
}