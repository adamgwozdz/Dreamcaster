package com.agiletech.dreamcaster.data.repository

import com.agiletech.dreamcaster.data.database.entity.DreamEntity
import com.agiletech.dreamcaster.data.Result
import com.agiletech.dreamcaster.data.database.entity.TagEntity
import com.agiletech.dreamcaster.data.model.Dream
import kotlinx.coroutines.flow.Flow

interface DreamsRepository {

    fun getDreamsStream(): Flow<Result<List<Dream>>>

    suspend fun saveDream(dream: DreamEntity, tags: List<TagEntity>)
}