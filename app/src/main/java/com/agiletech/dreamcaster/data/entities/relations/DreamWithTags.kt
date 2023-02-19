package com.agiletech.dreamcaster.data.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.agiletech.dreamcaster.data.entities.Dream
import com.agiletech.dreamcaster.data.entities.Tag

/**
 * Model class for a Dream and it's tags.
 *
 * @param dream dream object
 * @param tags list of tags
 */
data class DreamWithTags @JvmOverloads constructor(
    @Embedded val dream: Dream,
    @Relation(
        parentColumn = "id",
        entityColumn = "dreamId"
    )
    var tags: List<Tag>
)