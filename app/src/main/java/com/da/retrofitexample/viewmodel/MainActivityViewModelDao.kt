package com.da.retrofitexample.viewmodel

import androidx.lifecycle.*
import com.da.retrofitexample.model.ApiInterface
import com.da.retrofitexample.model.Hero
import com.da.retrofitexample.model.HeroEntity
import com.da.retrofitexample.model.HeroRepository
import kotlinx.coroutines.*

class MainActivityViewModelDao(val heroRepository: HeroRepository) : ViewModel() {

    //    private val repository: HeroRepository

    private  var liveHero  = MutableLiveData<List<HeroEntity>> ()
    val allHeros: LiveData<List<HeroEntity>> = heroRepository.allHeros.asLiveData()
    var job : Job? = null;

    fun insert(heroEntity: HeroEntity) = viewModelScope.launch {
        heroRepository.insert(heroEntity)
    }

    fun delete(value : String) = viewModelScope.launch{
        heroRepository.delete(value)
    }

    fun deleteAlldata() = viewModelScope.launch{
        heroRepository.deleteAll()
    }


    fun getLiveHero() : LiveData<List<HeroEntity>> {

        job = CoroutineScope(Dispatchers.IO).launch {

            val response = ApiInterface.create().getHeroesDao()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    liveHero.postValue(response.body())
                }
            }
        }
        return liveHero
    }


}

class MainActvityViewModelDaoFactory(private val repository: HeroRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModelDao::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainActivityViewModelDao(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
