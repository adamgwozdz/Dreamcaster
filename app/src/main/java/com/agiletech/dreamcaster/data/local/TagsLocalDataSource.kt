package com.agiletech.dreamcaster.data.local

import com.agiletech.dreamcaster.data.TagsDataSource
import com.agiletech.dreamcaster.data.entities.Tag
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TagsLocalDataSource internal constructor(
    private val tagsDao: TagsDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : TagsDataSource {

    override suspend fun saveTags(tags: List<Tag>) = withContext(ioDispatcher) {
        for (tag in tags) {
            tagsDao.insertTag(tag)
        }
    }
}