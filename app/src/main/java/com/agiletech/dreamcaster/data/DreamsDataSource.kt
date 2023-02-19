package com.agiletech.dreamcaster.data

import com.agiletech.dreamcaster.data.entities.Dream
import com.agiletech.dreamcaster.data.entities.relations.DreamWithTags
import kotlinx.coroutines.flow.Flow

interface DreamsDataSource {

    fun getDreamsStream(): Flow<Result<List<DreamWithTags>>>

    suspend fun getDreams(): Result<List<DreamWithTags>>

    suspend fun saveDream(dream: Dream)
}