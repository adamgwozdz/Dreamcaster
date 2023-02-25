package com.agiletech.dreamcaster.data.database.data_source

import com.agiletech.dreamcaster.data.Result
import com.agiletech.dreamcaster.data.database.entity.DreamEntity
import com.agiletech.dreamcaster.data.Result.Success
import com.agiletech.dreamcaster.data.database.dao.DreamsDao
import com.agiletech.dreamcaster.data.database.dao.TagsDao
import com.agiletech.dreamcaster.data.database.entity.TagEntity
import com.agiletech.dreamcaster.data.model.Dream
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext


class DreamsLocalDataSource internal constructor(
    private val dreamsDao: DreamsDao,
    private val tagsDao: TagsDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : DreamsDataSource {

    override fun getDreamsStream(): Flow<Result<List<Dream>>> {
        return dreamsDao.observeDreams().map { Success(it) }
    }

    override suspend fun saveDream(dream: DreamEntity) = withContext(ioDispatcher) {
        dreamsDao.insertDream(dream)
    }

    override suspend fun saveTags(tags: List<TagEntity>) = withContext(ioDispatcher) {
        for (tag in tags) {
            tagsDao.insertTag(tag)
        }
    }
}