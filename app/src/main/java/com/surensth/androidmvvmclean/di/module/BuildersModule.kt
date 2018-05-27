package com.surensth.androidmvvmclean.di.module

import com.surensth.androidmvvmclean.view.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by surensth on 27 May,2018
 */
@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeActivity(): HomeActivity
}