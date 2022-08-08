package com.da.retrofitexample.model

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class HeroRepository(private val heroDao: HeroDao) {

    val allHeros : Flow<List<HeroEntity>> = heroDao.getAllHeros()

    @Suppress("RedundantSuspendModifier")

    @WorkerThread
    suspend fun insert(heroEntity: HeroEntity){
        heroDao.insert(heroEntity)
    }

    @WorkerThread
    suspend fun deleteAll(){
        heroDao.deleteAllfromHeroEntity()
    }

    @WorkerThread
    suspend fun delete(value : String){
        heroDao.delete(value)
    }

}