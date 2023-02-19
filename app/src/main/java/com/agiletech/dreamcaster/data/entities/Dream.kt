package com.agiletech.dreamcaster.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Model class for a Dream.
 *
 * @param id id of a dream
 * @param title title of a dream
 * @param content content of a dream
 */

@Entity(tableName = "dreams")
data class Dream @JvmOverloads constructor(
    @PrimaryKey @ColumnInfo(name = "id") var id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "content") var content: String = "",
)