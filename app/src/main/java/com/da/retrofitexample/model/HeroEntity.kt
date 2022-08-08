package com.da.retrofitexample.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "hero_table")
data class HeroEntity(
    @PrimaryKey(autoGenerate = true) val value: Int,
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "realname") val realname : String,
    @ColumnInfo(name = "team") val team : String,
    @ColumnInfo(name = "firstappearance") val firstappearance : String,
    @ColumnInfo(name = "createdby") val createdby : String,
    @ColumnInfo(name = "publisher") val publisher : String,
    @ColumnInfo(name = "imageurl") val imageurl : String,
    @ColumnInfo(name = "bio") val bio : String
    )
