package com.da.retrofitexample

import android.app.Application
import android.content.res.Configuration
import com.da.retrofitexample.model.HeroRepository
import com.da.retrofitexample.model.HeroRoomDatabase
import com.da.retrofitexample.model.KoinModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.android.startKoin
import org.koin.standalone.KoinComponent

@InternalCoroutinesApi
class RetrofitExampleApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { HeroRoomDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { HeroRepository(database.heroDao()) }

    override fun onCreate(){
        super.onCreate()
        koinConfiguration()


    }

    private fun koinConfiguration(){
        startKoin(this, listOf(KoinModule))
    }

}