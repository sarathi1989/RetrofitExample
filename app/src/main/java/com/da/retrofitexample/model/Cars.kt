package com.da.retrofitexample.model

import java.util.stream.Collector
import java.util.stream.Stream

class Cars (val maruti : MarutiCar, val nission : NissonCar, val tataCar: TataCar) {

    fun displayModel(){
        maruti.model()
        nission.model()
        tataCar.model()

    }

    fun getModel() : List<String>{

        var items : ArrayList<String> = arrayListOf<String>()

        items.add(maruti.getModel())
        items.add(nission.getModel())
        items.add(tataCar.getModel())



        return items

    }




}