package com.agiletech.dreamcaster.data

import com.agiletech.dreamcaster.data.entities.Tag

interface TagsDataSource {

    suspend fun saveTags(tags: List<Tag>)
}