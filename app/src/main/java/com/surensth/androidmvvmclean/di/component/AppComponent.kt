package com.surensth.androidmvvmclean.di.component

import com.surensth.androidmvvmclean.MyApplication
import com.surensth.androidmvvmclean.di.module.AppModule
import com.surensth.androidmvvmclean.di.module.BuildersModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(AndroidInjectionModule::class, BuildersModule::class, AppModule::class)
)
interface AppComponent {
  fun inject(app: MyApplication)
}