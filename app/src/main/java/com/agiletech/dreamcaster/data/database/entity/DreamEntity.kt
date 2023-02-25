package com.agiletech.dreamcaster.data.database.entity

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
 * @param dateCreated date of creation
 * @param dateModified date of modification
 * @param isLucid is dream lucid?
 * @param isNightmare is dream a nightmare?
 * @param isRecurring is dream a recurring dream?
 * @param quality quality of a dream
 * @param clarity clarity of a dream
 */

@Entity(tableName = "dreams")
data class DreamEntity @JvmOverloads constructor(
    @PrimaryKey @ColumnInfo(name = "id") var id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "content") var content: String = "",
    @ColumnInfo(name = "dateCreated") var dateCreated: String = "", //TODO Change to Timestamp and add @TypeConverter
    @ColumnInfo(name = "dateModified") var dateModified: String = "", //TODO Change to Timestamp and add @TypeConverter
    @ColumnInfo(name = "lucid") var isLucid: Boolean = false,
    @ColumnInfo(name = "nightmare") var isNightmare: Boolean = false,
    @ColumnInfo(name = "recurring") var isRecurring: Boolean = false,
    @ColumnInfo(name = "quality") var quality: String = "",
    @ColumnInfo(name = "clarity") var clarity: String = ""
)