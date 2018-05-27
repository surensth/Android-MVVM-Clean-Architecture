package com.surensth.androidmvvmclean

import android.app.Activity
import android.app.Application
import com.surensth.androidmvvmclean.di.component.DaggerAppComponent
import com.surensth.androidmvvmclean.di.module.AppModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by surensth on 27 May,2018
 */
class MyApplication:Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        //DaggerAppComponent is obtained in runtime from AppComponent
        DaggerAppComponent.builder().appModule(AppModule(this)).build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

}