package com.da.retrofitexample.model

import com.da.retrofitexample.viewmodel.MainActivityViewModelDao
import com.da.retrofitexample.viewmodel.MainActvityViewModel
import org.koin.androidx.viewmodel.experimental.builder.viewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module


val KoinModule = module {
    viewModel { MainActvityViewModel()}

    viewModel{MainActivityViewModelDao(get ())}
 //   factory { HeroRepository(get()) }



    single { Hero() }
    factory { TataCar() }
    factory { NissonCar() }
    single { MarutiCar() }
    single { Cars(get(),get(),get()) }


}


