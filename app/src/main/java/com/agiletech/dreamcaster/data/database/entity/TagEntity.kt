package com.agiletech.dreamcaster.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Model class for a Tag.
 *
 * @param id id of a tag
 * @param dreamId id of a dream that has this tag
 * @param name name of a tag
 */
@Entity(tableName = "tags")
data class TagEntity @JvmOverloads constructor(
    @PrimaryKey @ColumnInfo(name = "id") var id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "dreamId") var dreamId: String = "",
    @ColumnInfo(name = "name") var name: String = ""
)