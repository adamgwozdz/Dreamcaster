package com.agiletech.dreamcaster.domain.repository

import com.agiletech.dreamcaster.data.entities.Dream
import com.agiletech.dreamcaster.data.Result
import com.agiletech.dreamcaster.data.entities.Tag
import com.agiletech.dreamcaster.data.entities.relations.DreamWithTags
import kotlinx.coroutines.flow.Flow

interface DreamsRepository {

    fun getDreamsStream(): Flow<Result<List<DreamWithTags>>>

    suspend fun getDreams(): Result<List<DreamWithTags>>

    suspend fun saveDream(dream: Dream, tags: List<Tag>)
}