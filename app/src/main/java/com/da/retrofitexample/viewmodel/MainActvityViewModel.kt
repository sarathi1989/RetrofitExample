package com.da.retrofitexample.viewmodel

import androidx.lifecycle.*
import com.da.retrofitexample.model.*
import kotlinx.coroutines.*


class MainActvityViewModel : ViewModel() {

    private  var liveHero  = MutableLiveData<List<Hero>> ()
    private var liveCarDetails = MutableLiveData<List<String>> ()

    private var btndata = MutableLiveData<String>()

    var job : Job? = null;


    fun onButtonClicked(){


        callBtnfun()
    }

    fun callBtnfun () : LiveData<String>{


        btndata.postValue("btnclicked")



        //Toast.makeText(this,"Button Clicked",Toast.LENGTH_SHORT).show()
return btndata
    }

    fun getLiveHero() : LiveData<List<Hero>>{

        job = CoroutineScope(Dispatchers.IO).launch {

            val response = ApiInterface.create().getHeroes()

            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    liveHero.postValue (response.body())
                }
            }

        }

        /*val apiInterface = ApiInterface.create().getHeroes()

        apiInterface.enqueue(object  : Callback<List<Hero>>{
            override fun onResponse(call: Call<List<Hero>>, response: Response<List<Hero>>) {

                liveHero.postValue(response.body())

        } override fun onFailure(call: Call<List<Hero>>, t: Throwable) {



        }
    })*/


        return liveHero

    }

    fun getCarDetails(car : Cars) : LiveData<List<String>>
    {
        liveCarDetails.postValue(car.getModel())
        return liveCarDetails;
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}
//val somename  = module{  viewModel{ MainActvityViewModel() } }


