package com.agiletech.dreamcaster.data.repository

import com.agiletech.dreamcaster.data.DreamsDataSource
import com.agiletech.dreamcaster.data.Result
import com.agiletech.dreamcaster.data.TagsDataSource
import com.agiletech.dreamcaster.data.entities.Dream
import com.agiletech.dreamcaster.data.entities.Tag
import com.agiletech.dreamcaster.data.entities.relations.DreamWithTags
import com.agiletech.dreamcaster.domain.repository.DreamsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DreamsRepositoryImpl(
    private val dreamsLocalDataSource: DreamsDataSource,
    private val tagsLocalDataSource: TagsDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : DreamsRepository {

    override fun getDreamsStream(): Flow<Result<List<DreamWithTags>>> {
        return dreamsLocalDataSource.getDreamsStream()
    }

    override suspend fun getDreams(): Result<List<DreamWithTags>> {
        return try {
            dreamsLocalDataSource.getDreams()
        } catch (e: java.lang.Exception) {
            Result.Error(e)
        }
    }

    override suspend fun saveDream(dream: Dream, tags: List<Tag>) {
        coroutineScope {
            launch { dreamsLocalDataSource.saveDream(dream) }
            launch { tagsLocalDataSource.saveTags(tags) }
        }
    }
}