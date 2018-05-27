package com.surensth.androidmvvmclean.di.module

import com.surensth.androidmvvmclean.MyApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: MyApplication) {

    @Provides
    @Singleton
    fun provideApplication(): MyApplication = app
}