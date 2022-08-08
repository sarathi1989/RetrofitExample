package com.da.retrofitexample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.da.retrofitexample.R
import com.da.retrofitexample.view.ui.main.MainFragment

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}