package com.agiletech.dreamcaster.data.model

import androidx.room.Embedded
import androidx.room.Relation
import com.agiletech.dreamcaster.data.database.entity.DreamEntity
import com.agiletech.dreamcaster.data.database.entity.TagEntity

/**
 * Model class for a Dream and it's tags.
 *
 * @param dream dream object
 * @param tags list of tags
 */
data class Dream @JvmOverloads constructor(
    @Embedded val dream: DreamEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "dreamId"
    )
    var tags: List<TagEntity>
)