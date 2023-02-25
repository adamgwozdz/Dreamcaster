package com.agiletech.dreamcaster.data.repository

import com.agiletech.dreamcaster.data.database.data_source.DreamsDataSource
import com.agiletech.dreamcaster.data.Result
import com.agiletech.dreamcaster.data.database.entity.DreamEntity
import com.agiletech.dreamcaster.data.database.entity.TagEntity
import com.agiletech.dreamcaster.data.model.Dream
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DefaultDreamsRepository(
    private val dreamsLocalDataSource: DreamsDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : DreamsRepository {

    override fun getDreamsStream(): Flow<Result<List<Dream>>> {
        return dreamsLocalDataSource.getDreamsStream()
    }

    override suspend fun saveDream(dream: DreamEntity, tags: List<TagEntity>) {
        coroutineScope {
            launch { dreamsLocalDataSource.saveDream(dream) }
            launch { dreamsLocalDataSource.saveTags(tags) }
        }
    }
}