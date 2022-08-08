package com.da.retrofitexample.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.jetbrains.annotations.NotNull

import androidx.room.Update

import androidx.room.Delete




@Dao
interface HeroDao {
    @Query("Select * from hero_table order by name ASC")
    fun getAllHeros() : Flow<List<HeroEntity>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert (heroEntity: HeroEntity)

    @Query("DELETE FROM hero_table")
    suspend fun deleteAllfromHeroEntity()

    @Query("Delete from hero_table where name = :value")
    suspend fun delete(value: String)

}