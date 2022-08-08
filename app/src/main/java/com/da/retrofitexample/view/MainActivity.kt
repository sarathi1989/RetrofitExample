package com.da.retrofitexample.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.telecom.TelecomManager
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.da.retrofitexample.R
import com.da.retrofitexample.databinding.ActivityMainBinding
import com.da.retrofitexample.model.Cars
import com.da.retrofitexample.model.Hero
import com.da.retrofitexample.viewmodel.MainActvityViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.security.keystore.KeyProperties

import android.security.keystore.KeyGenParameterSpec
import java.security.KeyPair
import java.security.KeyPairGenerator
import android.security.KeyPairGeneratorSpec
import android.widget.Toast
import java.io.File
import java.math.BigInteger
import javax.security.auth.x500.X500Principal
import android.os.Environment
import androidx.lifecycle.ViewModelProvider
import com.da.retrofitexample.RetrofitExampleApplication
import com.da.retrofitexample.model.HeroEntity
import com.da.retrofitexample.model.HeroRepository
import com.da.retrofitexample.viewmodel.MainActivityViewModelDao
import com.da.retrofitexample.viewmodel.MainActvityViewModelDaoFactory
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.ext.android.get
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var names : String? = null
    lateinit var recyclewViewItem :  RecyclewViewItem
    lateinit var linearLayoutManager : LinearLayoutManager
    private val mainActvityViewModel : MainActvityViewModel by viewModel()

    @InternalCoroutinesApi
    private val mainActivityViewModelDao: MainActivityViewModelDao by viewModels {
        MainActvityViewModelDaoFactory((application as RetrofitExampleApplication).repository)
    }

    //var mainActivityViewModelDao : MainActivityViewModelDao? = null
    val herokoin : Hero by inject()
    val car : Cars by inject()
    @OptIn(InternalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         binding  = DataBindingUtil.setContentView(this, R.layout.activity_main)

       // setContentView(R.layout.activity_main)

        linearLayoutManager = GridLayoutManager(applicationContext,2)
        binding.recyclerView.layoutManager = linearLayoutManager

        binding.button3.setOnClickListener{

            var intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)

        }

       // startKoin(this, listOf(somename, heromodule))
        //mainActvityViewModel = MainActvityViewModel() by viewModel()
        //mainActvityViewModel  = ViewModelProvider(this)[MainActvityViewModel::class.java]
        //mainActivityViewModelDao = ViewModelProvider(this)[MainActivityViewModelDao::class.java]
      /* val kpg: KeyPairGenerator = KeyPairGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_RSA, "AndroidKeyStore"
        )

        val spec = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            KeyGenParameterSpec.Builder(alias ?: "", KeyProperties.PURPOSE_DECRYPT)
                .setDigests(KeyProperties.D, KeyProperties.DIGEST_SHA512)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
                .build()
        } else {
            TODO("VERSION.SDK_INT < M")
        }

        kpg.initialize(spec)
        val keyPair = kpg.generateKeyPair()*/


    //val files = File(this.getExternalFilesDir(Environment.DIRECTORY_DCIM).toString()+"/Log/da")
        //val file = File(Environment.getE().toString() + "/Sample Directory")
       // val directory = File("/existingdir/dir")

        //val files = File(this.getExternalFilesDir(Environment.getDataDirectory().name).toString()+"/Log/da")
        //val files = File("/data/ola/Log/da")
        /*if (files.mkdirs()) {
          Toast.makeText(this,"file created success!",Toast.LENGTH_LONG).show()
          }else{
            Toast.makeText(this,"file  already created !",Toast.LENGTH_LONG).show()
        }*/


        mainActvityViewModel.callBtnfun().observeForever {
            Toast.makeText(this,it,Toast.LENGTH_LONG).show()
        }



        mainActvityViewModel.getLiveHero().observe(this) {
            it?.let { herolist ->

              //  recyclewViewItem = RecyclewViewItem(herokoin, applicationContext, herolist)
               // binding.recyclerView.adapter = recyclewViewItem
                car.displayModel()


            }


        }



        mainActvityViewModel.getCarDetails(car).observe(this) {
            it?.let { carItem ->

                for (i in 0 until carItem.size) {

                    Log.v("cars", "getCardetails" + carItem.get(i))
                }

            }
        }


        /*val apiInterface = ApiInterface.create().getHeroes()


        apiInterface.enqueue(object  : Callback<List<Hero>>{
            override fun onResponse(call: Call<List<Hero>>, response: Response<List<Hero>>) {

               // Log.v("Sarathi onResponse","response" +response)

                val listItem : List<Hero>? = response.body()
                recyclewViewItem = RecyclewViewItem(applicationContext, listItem)
                binding.recyclerView.adapter = recyclewViewItem;


*//*
                listItem?.forEach { it->

                    Log.v("Sarathi onResponse","Name" +it.getName())

                    *//**//*if(names == null){
                        names = "\n" + it.getName()
                    }else{
                        names = names + "\n" + it.getName()
                    }*//**//*

                }*//*
               // binding.textView1.text = names


            }

            override fun onFailure(call: Call<List<Hero>>, t: Throwable) {

                Log.v("Sarathi onFailure","onFailure" +t)

            }
            })*/



        mainActivityViewModelDao.getLiveHero().observe(this) {
            it?.let { herolist ->

               mainActivityViewModelDao.deleteAlldata()
                for (i in herolist)
                    mainActivityViewModelDao.insert(i)
            }


        }

        mainActivityViewModelDao.allHeros.observeForever(){
            it?.let { HeroEntityList->

                recyclewViewItem = RecyclewViewItem( applicationContext, HeroEntityList,mainActivityViewModelDao)
                binding.recyclerView.adapter = recyclewViewItem

                Log.v("Sarathi","get Item from DB :${HeroEntityList}" )

            }
        }


    }

    override fun onDestroy() {
        super.onDestroy()
       // stopKoin()
    }
}

