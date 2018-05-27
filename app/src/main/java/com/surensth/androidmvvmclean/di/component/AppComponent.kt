package com.surensth.androidmvvmclean.di.component

import com.surensth.androidmvvmclean.MyApplication
import com.surensth.androidmvvmclean.di.module.AppModule
import com.surensth.androidmvvmclean.di.module.BuildersModule
import com.surensth.androidmvvmclean.di.module.NetModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(AndroidInjectionModule::class, BuildersModule::class, AppModule::class, NetModule::class)
)
interface AppComponent {
  fun inject(app: MyApplication)
}