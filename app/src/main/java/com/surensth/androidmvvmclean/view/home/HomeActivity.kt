package com.surensth.androidmvvmclean.view.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.surensth.androidmvvmclean.R
import dagger.android.AndroidInjection

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        AndroidInjection.inject(this)

    }
}
