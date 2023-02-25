package com.agiletech.dreamcaster.data.database.data_source

import com.agiletech.dreamcaster.data.Result
import com.agiletech.dreamcaster.data.database.entity.DreamEntity
import com.agiletech.dreamcaster.data.database.entity.TagEntity
import com.agiletech.dreamcaster.data.model.Dream
import kotlinx.coroutines.flow.Flow

interface DreamsDataSource {

    fun getDreamsStream(): Flow<Result<List<Dream>>>

    suspend fun saveDream(dream: DreamEntity)

    suspend fun saveTags(tags: List<TagEntity>)
}